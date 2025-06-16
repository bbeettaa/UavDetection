# UAV Identification Software
In the context of the availability and rapid development of UAVs, 
there is a need for systems for their prompt detection and tracking. 
Analysis of existing software solutions showed that most of the solutions are designed for stationary ground monitoring posts, 
integrating multi-sensor complexes (video analytics, RF scanning) with centralized servers. 

This shows the following limitations of existing systems: 
- Lack of support for video stream from the UAV board.
- Closed architecture and hardware dependency that do not allow software modification for specific scenarios. 
- Narrow specialization – focus on protecting the perimeters of critical infrastructure facilities from fixed points, prevents systems
from scaling to mobile platforms and multi-purpose applications. 

Therefore, there is a need to create software that: 
- It will work in real time on board a moving UAV. 
- Will have an open and scalable architecture for the further development of new algorithms.


## Architecture Design 
The software is built on the principle of multi-threaded buffered pipeline architecture. 
Each stage of video frame processing: decoding > detection> tracking > visualization, are performed each in a separate stream, 
and frames are passed through buffer queues for asynchrony and uniform loading.

![state Diagram MOT](/PhotoReadme/Architecture.png)

## Multitracking Design 
The main difficulty lies in the stable preservation of the identity of objects between frames, despite occlusions, 
changes in scale, loss of detection or intersection of objects. For such a task, the TrackingManager 
class has been developed, which makes tracking of several objects in a video stream in real time.

![state Diagram MOT](/PhotoReadme/stateDiagramMOT.svg)

The association is based on a Hungarian-type algorithm based on IoU (HungarianIoUAsassociation), 
which matches new detections with existing tracks, which allows you to accurately match rectangles in different frames while maintaining the identified object.

![MOT](/PhotoReadme/MotManager.png)

Managing the life cycle of a track is necessarily aimed at confirming the associated tracks. 

This process is carried out through confirmation algorithms: 

- NoutOfMConfirmation, hitting the dector N times of M 
- MaxMissedDeletingAlgorithm confirmation (maximum number of frame skips), assigning a state to deletion.

To avoid premature removal of the object during a short-term loss of detection (e.g. due to sudden acceleration or partial occlusion), 
The MOT uses predicting the future state of the track through a tracking algorithm selected by the user. 

This strategy works especially well with algorithms such as the Kalman filter or CSRT, which are able to predict the position of an object even without actual detection.

## MOT Manager

![Res1](/PhotoReadme/rec1.gif)


# Results
![Res2](/PhotoReadme/rec2.gif)


