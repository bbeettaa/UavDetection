from google.protobuf import empty_pb2 as _empty_pb2
from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class TrackerConfig(_message.Message):
    __slots__ = ["lost_track_buffer", "track_activation_threshold", "minimum_consecutive_frames", "minimum_iou_threshold"]
    LOST_TRACK_BUFFER_FIELD_NUMBER: _ClassVar[int]
    TRACK_ACTIVATION_THRESHOLD_FIELD_NUMBER: _ClassVar[int]
    MINIMUM_CONSECUTIVE_FRAMES_FIELD_NUMBER: _ClassVar[int]
    MINIMUM_IOU_THRESHOLD_FIELD_NUMBER: _ClassVar[int]
    lost_track_buffer: int
    track_activation_threshold: float
    minimum_consecutive_frames: int
    minimum_iou_threshold: float
    def __init__(self, lost_track_buffer: _Optional[int] = ..., track_activation_threshold: _Optional[float] = ..., minimum_consecutive_frames: _Optional[int] = ..., minimum_iou_threshold: _Optional[float] = ...) -> None: ...

class FrameRequest(_message.Message):
    __slots__ = ["image", "detections"]
    IMAGE_FIELD_NUMBER: _ClassVar[int]
    DETECTIONS_FIELD_NUMBER: _ClassVar[int]
    image: bytes
    detections: _containers.RepeatedCompositeFieldContainer[Detection]
    def __init__(self, image: _Optional[bytes] = ..., detections: _Optional[_Iterable[_Union[Detection, _Mapping]]] = ...) -> None: ...

class Detection(_message.Message):
    __slots__ = ["x", "y", "width", "height", "confidence", "class_name"]
    X_FIELD_NUMBER: _ClassVar[int]
    Y_FIELD_NUMBER: _ClassVar[int]
    WIDTH_FIELD_NUMBER: _ClassVar[int]
    HEIGHT_FIELD_NUMBER: _ClassVar[int]
    CONFIDENCE_FIELD_NUMBER: _ClassVar[int]
    CLASS_NAME_FIELD_NUMBER: _ClassVar[int]
    x: float
    y: float
    width: float
    height: float
    confidence: float
    class_name: str
    def __init__(self, x: _Optional[float] = ..., y: _Optional[float] = ..., width: _Optional[float] = ..., height: _Optional[float] = ..., confidence: _Optional[float] = ..., class_name: _Optional[str] = ...) -> None: ...

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
