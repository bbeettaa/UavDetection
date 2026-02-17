from google.protobuf import empty_pb2 as _empty_pb2
from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class TrackerConfig(_message.Message):
    __slots__ = ["max_iou_distance", "max_age", "n_init", "nms_max_overlap", "max_cosine_distance", "nn_budget", "gating_only_position", "override_track_class", "embedder", "half", "bgr", "embedder_gpu", "embedder_model_name", "embedder_wts", "polygon", "today"]
    MAX_IOU_DISTANCE_FIELD_NUMBER: _ClassVar[int]
    MAX_AGE_FIELD_NUMBER: _ClassVar[int]
    N_INIT_FIELD_NUMBER: _ClassVar[int]
    NMS_MAX_OVERLAP_FIELD_NUMBER: _ClassVar[int]
    MAX_COSINE_DISTANCE_FIELD_NUMBER: _ClassVar[int]
    NN_BUDGET_FIELD_NUMBER: _ClassVar[int]
    GATING_ONLY_POSITION_FIELD_NUMBER: _ClassVar[int]
    OVERRIDE_TRACK_CLASS_FIELD_NUMBER: _ClassVar[int]
    EMBEDDER_FIELD_NUMBER: _ClassVar[int]
    HALF_FIELD_NUMBER: _ClassVar[int]
    BGR_FIELD_NUMBER: _ClassVar[int]
    EMBEDDER_GPU_FIELD_NUMBER: _ClassVar[int]
    EMBEDDER_MODEL_NAME_FIELD_NUMBER: _ClassVar[int]
    EMBEDDER_WTS_FIELD_NUMBER: _ClassVar[int]
    POLYGON_FIELD_NUMBER: _ClassVar[int]
    TODAY_FIELD_NUMBER: _ClassVar[int]
    max_iou_distance: float
    max_age: int
    n_init: int
    nms_max_overlap: float
    max_cosine_distance: float
    nn_budget: int
    gating_only_position: bool
    override_track_class: str
    embedder: str
    half: bool
    bgr: bool
    embedder_gpu: bool
    embedder_model_name: str
    embedder_wts: str
    polygon: bool
    today: str
    def __init__(self, max_iou_distance: _Optional[float] = ..., max_age: _Optional[int] = ..., n_init: _Optional[int] = ..., nms_max_overlap: _Optional[float] = ..., max_cosine_distance: _Optional[float] = ..., nn_budget: _Optional[int] = ..., gating_only_position: bool = ..., override_track_class: _Optional[str] = ..., embedder: _Optional[str] = ..., half: bool = ..., bgr: bool = ..., embedder_gpu: bool = ..., embedder_model_name: _Optional[str] = ..., embedder_wts: _Optional[str] = ..., polygon: bool = ..., today: _Optional[str] = ...) -> None: ...

class Detection(_message.Message):
    __slots__ = ["class_name", "class_id", "confidence", "x", "y", "width", "height"]
    CLASS_NAME_FIELD_NUMBER: _ClassVar[int]
    CLASS_ID_FIELD_NUMBER: _ClassVar[int]
    CONFIDENCE_FIELD_NUMBER: _ClassVar[int]
    X_FIELD_NUMBER: _ClassVar[int]
    Y_FIELD_NUMBER: _ClassVar[int]
    WIDTH_FIELD_NUMBER: _ClassVar[int]
    HEIGHT_FIELD_NUMBER: _ClassVar[int]
    class_name: str
    class_id: int
    confidence: float
    x: float
    y: float
    width: float
    height: float
    def __init__(self, class_name: _Optional[str] = ..., class_id: _Optional[int] = ..., confidence: _Optional[float] = ..., x: _Optional[float] = ..., y: _Optional[float] = ..., width: _Optional[float] = ..., height: _Optional[float] = ...) -> None: ...

class FrameRequest(_message.Message):
    __slots__ = ["session_id", "timestamp", "image", "detections"]
    SESSION_ID_FIELD_NUMBER: _ClassVar[int]
    TIMESTAMP_FIELD_NUMBER: _ClassVar[int]
    IMAGE_FIELD_NUMBER: _ClassVar[int]
    DETECTIONS_FIELD_NUMBER: _ClassVar[int]
    session_id: str
    timestamp: int
    image: bytes
    detections: _containers.RepeatedCompositeFieldContainer[Detection]
    def __init__(self, session_id: _Optional[str] = ..., timestamp: _Optional[int] = ..., image: _Optional[bytes] = ..., detections: _Optional[_Iterable[_Union[Detection, _Mapping]]] = ...) -> None: ...

class TrackedObject(_message.Message):
    __slots__ = ["id", "class_name", "class_id", "confidence", "x", "y", "width", "height"]
    ID_FIELD_NUMBER: _ClassVar[int]
    CLASS_NAME_FIELD_NUMBER: _ClassVar[int]
    CLASS_ID_FIELD_NUMBER: _ClassVar[int]
    CONFIDENCE_FIELD_NUMBER: _ClassVar[int]
    X_FIELD_NUMBER: _ClassVar[int]
    Y_FIELD_NUMBER: _ClassVar[int]
    WIDTH_FIELD_NUMBER: _ClassVar[int]
    HEIGHT_FIELD_NUMBER: _ClassVar[int]
    id: str
    class_name: str
    class_id: int
    confidence: float
    x: float
    y: float
    width: float
    height: float
    def __init__(self, id: _Optional[str] = ..., class_name: _Optional[str] = ..., class_id: _Optional[int] = ..., confidence: _Optional[float] = ..., x: _Optional[float] = ..., y: _Optional[float] = ..., width: _Optional[float] = ..., height: _Optional[float] = ...) -> None: ...

class InitResponse(_message.Message):
    __slots__ = ["success"]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    success: bool
    def __init__(self, success: bool = ...) -> None: ...

class UpdateResponse(_message.Message):
    __slots__ = ["tracks"]
    TRACKS_FIELD_NUMBER: _ClassVar[int]
    tracks: _containers.RepeatedCompositeFieldContainer[TrackedObject]
    def __init__(self, tracks: _Optional[_Iterable[_Union[TrackedObject, _Mapping]]] = ...) -> None: ...

class BufferCapacityResponse(_message.Message):
    __slots__ = ["capacity"]
    CAPACITY_FIELD_NUMBER: _ClassVar[int]
    capacity: int
    def __init__(self, capacity: _Optional[int] = ...) -> None: ...

class SessionRequest(_message.Message):
    __slots__ = ["session_id"]
    SESSION_ID_FIELD_NUMBER: _ClassVar[int]
    session_id: str
    def __init__(self, session_id: _Optional[str] = ...) -> None: ...
