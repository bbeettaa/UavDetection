from google.protobuf import empty_pb2 as _empty_pb2
from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class SsdConfig(_message.Message):
    __slots__ = ["model_path", "input_width", "input_height", "confidence_threshold", "nms_threshold", "max_detections"]
    MODEL_PATH_FIELD_NUMBER: _ClassVar[int]
    INPUT_WIDTH_FIELD_NUMBER: _ClassVar[int]
    INPUT_HEIGHT_FIELD_NUMBER: _ClassVar[int]
    CONFIDENCE_THRESHOLD_FIELD_NUMBER: _ClassVar[int]
    NMS_THRESHOLD_FIELD_NUMBER: _ClassVar[int]
    MAX_DETECTIONS_FIELD_NUMBER: _ClassVar[int]
    model_path: str
    input_width: int
    input_height: int
    confidence_threshold: float
    nms_threshold: float
    max_detections: int
    def __init__(self, model_path: _Optional[str] = ..., input_width: _Optional[int] = ..., input_height: _Optional[int] = ..., confidence_threshold: _Optional[float] = ..., nms_threshold: _Optional[float] = ..., max_detections: _Optional[int] = ...) -> None: ...

class GetConfigResponse(_message.Message):
    __slots__ = ["config"]
    CONFIG_FIELD_NUMBER: _ClassVar[int]
    config: SsdConfig
    def __init__(self, config: _Optional[_Union[SsdConfig, _Mapping]] = ...) -> None: ...

class SessionRequest(_message.Message):
    __slots__ = ["session_id"]
    SESSION_ID_FIELD_NUMBER: _ClassVar[int]
    session_id: str
    def __init__(self, session_id: _Optional[str] = ...) -> None: ...

class StreamStatus(_message.Message):
    __slots__ = ["success", "message"]
    SUCCESS_FIELD_NUMBER: _ClassVar[int]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    success: bool
    message: str
    def __init__(self, success: bool = ..., message: _Optional[str] = ...) -> None: ...

class FrameRequest(_message.Message):
    __slots__ = ["session_id", "image", "timestamp"]
    SESSION_ID_FIELD_NUMBER: _ClassVar[int]
    IMAGE_FIELD_NUMBER: _ClassVar[int]
    TIMESTAMP_FIELD_NUMBER: _ClassVar[int]
    session_id: str
    image: bytes
    timestamp: int
    def __init__(self, session_id: _Optional[str] = ..., image: _Optional[bytes] = ..., timestamp: _Optional[int] = ...) -> None: ...

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

class TrackingResult(_message.Message):
    __slots__ = ["timestamp", "detections"]
    TIMESTAMP_FIELD_NUMBER: _ClassVar[int]
    DETECTIONS_FIELD_NUMBER: _ClassVar[int]
    timestamp: int
    detections: _containers.RepeatedCompositeFieldContainer[Detection]
    def __init__(self, timestamp: _Optional[int] = ..., detections: _Optional[_Iterable[_Union[Detection, _Mapping]]] = ...) -> None: ...
