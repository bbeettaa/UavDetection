package knu.app.bll.mot;

import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Rect;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.MaximumWeightBipartiteMatching;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;
import java.util.logging.Logger;

public class HungarianIoUAssociationJGraphT implements AssociationAlgorithm {
    private static final Logger logger = Logger.getLogger(HungarianIoUAssociationJGraphT.class.getName());
    private double iouThreshold;

    public HungarianIoUAssociationJGraphT(double iouThreshold) {
        this.iouThreshold = iouThreshold;
    }

    @Override
    public Map<TrackedObject, Rect> associate(List<TrackedObject> tracks, DetectionResult detections) {
        List<Rect> detRects = detections.getRects();
        Map<TrackedObject, Rect> result = new HashMap<>();

        if (tracks.isEmpty() || detRects.isEmpty()) {
            return result;
        }

        try {
            Graph<Object, DefaultWeightedEdge> graph = buildGraphOnIoU(tracks, detRects);
            Set<DefaultWeightedEdge> matching = performMatching(graph, new HashSet<>(tracks), new HashSet<>(detRects));
            result = extractMatchingResults(graph, matching);
        } catch (NumberFormatException e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
        }

        return result;
    }

    private Graph<Object, DefaultWeightedEdge> buildGraphOnIoU(List<TrackedObject> tracks, List<Rect> detRects) {
        Graph<Object, DefaultWeightedEdge> graph = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);

        Set<Object> trackVertices = new HashSet<>(tracks);
        Set<Object> detVertices = new HashSet<>(detRects);

        trackVertices.forEach(graph::addVertex);
        detVertices.forEach(graph::addVertex);

        for (TrackedObject track : tracks) {
            for (Rect det : detRects) {
                double iou = Utils.computeIoU(track.getRect(), det);
                if (iou >= iouThreshold) {
                    DefaultWeightedEdge edge = graph.addEdge(track, det);
                    if (edge != null) {
                        graph.setEdgeWeight(edge, 1.0 - iou);
                    }
                }
            }
        }

        return graph;
    }

    private Set<DefaultWeightedEdge> performMatching(Graph<Object, DefaultWeightedEdge> graph,
                                                     Set<Object> trackVertices, Set<Object> detVertices) {
        MaximumWeightBipartiteMatching<Object, DefaultWeightedEdge> matchingAlg =
                new MaximumWeightBipartiteMatching<>(graph, trackVertices, detVertices);
        return matchingAlg.getMatching().getEdges();
    }

    private Map<TrackedObject, Rect> extractMatchingResults(Graph<Object, DefaultWeightedEdge> graph,
                                                            Set<DefaultWeightedEdge> matching) {
        Map<TrackedObject, Rect> result = new HashMap<>();
        for (DefaultWeightedEdge edge : matching) {
            Object src = graph.getEdgeSource(edge);
            Object tgt = graph.getEdgeTarget(edge);

            if (src instanceof TrackedObject && tgt instanceof Rect) {
                result.put((TrackedObject) src, (Rect) tgt);
            } else if (tgt instanceof TrackedObject && src instanceof Rect) {
                result.put((TrackedObject) tgt, (Rect) src);
            }
        }
        return result;
    }


    @Override
    public double getIouThreshold() {
        return iouThreshold;
    }

    @Override
    public void setIouThreshold(double iou) {
        iouThreshold = iou;
    }


}
