from google.protobuf import empty_pb2 as _empty_pb2
from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class TrackerConfig(_message.Message):
    __slots__ = ["track_thresh", "high_thresh", "match_thresh", "track_buffer"]
    TRACK_THRESH_FIELD_NUMBER: _ClassVar[int]
    HIGH_THRESH_FIELD_NUMBER: _ClassVar[int]
    MATCH_THRESH_FIELD_NUMBER: _ClassVar[int]
    TRACK_BUFFER_FIELD_NUMBER: _ClassVar[int]
    track_thresh: float
    high_thresh: float
    match_thresh: float
    track_buffer: int
    def __init__(self, track_thresh: _Optional[float] = ..., high_thresh: _Optional[float] = ..., match_thresh: _Optional[float] = ..., track_buffer: _Optional[int] = ...) -> None: ...

class InitRequest(_message.Message):
    __slots__ = ["config"]
    CONFIG_FIELD_NUMBER: _ClassVar[int]
    config: TrackerConfig
    def __init__(self, config: _Optional[_Union[TrackerConfig, _Mapping]] = ...) -> None: ...

class InitResponse(_message.Message):
    __slots__ = ["success"]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    success: bool
    def __init__(self, success: bool = ...) -> None: ...

class Detection(_message.Message):
    __slots__ = ["class_name", "confidence", "x", "y", "width", "height"]
    CLASS_NAME_FIELD_NUMBER: _ClassVar[int]
    CONFIDENCE_FIELD_NUMBER: _ClassVar[int]
    X_FIELD_NUMBER: _ClassVar[int]
    Y_FIELD_NUMBER: _ClassVar[int]
    WIDTH_FIELD_NUMBER: _ClassVar[int]
    HEIGHT_FIELD_NUMBER: _ClassVar[int]
    class_name: str
    confidence: float
    x: float
    y: float
    width: float
    height: float
    def __init__(self, class_name: _Optional[str] = ..., confidence: _Optional[float] = ..., x: _Optional[float] = ..., y: _Optional[float] = ..., width: _Optional[float] = ..., height: _Optional[float] = ...) -> None: ...

class DetectionResult(_message.Message):
    __slots__ = ["detections"]
    DETECTIONS_FIELD_NUMBER: _ClassVar[int]
    detections: _containers.RepeatedCompositeFieldContainer[Detection]
    def __init__(self, detections: _Optional[_Iterable[_Union[Detection, _Mapping]]] = ...) -> None: ...

class TrackedObject(_message.Message):
    __slots__ = ["id", "class_name", "confidence", "x", "y", "width", "height"]
    ID_FIELD_NUMBER: _ClassVar[int]
    CLASS_NAME_FIELD_NUMBER: _ClassVar[int]
    CONFIDENCE_FIELD_NUMBER: _ClassVar[int]
    X_FIELD_NUMBER: _ClassVar[int]
    Y_FIELD_NUMBER: _ClassVar[int]
    WIDTH_FIELD_NUMBER: _ClassVar[int]
    HEIGHT_FIELD_NUMBER: _ClassVar[int]
    id: str
    class_name: str
    confidence: float
    x: float
    y: float
    width: float
    height: float
    def __init__(self, id: _Optional[str] = ..., class_name: _Optional[str] = ..., confidence: _Optional[float] = ..., x: _Optional[float] = ..., y: _Optional[float] = ..., width: _Optional[float] = ..., height: _Optional[float] = ...) -> None: ...

class UpdateRequest(_message.Message):
    __slots__ = ["frame", "det_result"]
    FRAME_FIELD_NUMBER: _ClassVar[int]
    DET_RESULT_FIELD_NUMBER: _ClassVar[int]
    frame: bytes
    det_result: DetectionResult
    def __init__(self, frame: _Optional[bytes] = ..., det_result: _Optional[_Union[DetectionResult, _Mapping]] = ...) -> None: ...

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
