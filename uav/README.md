


```bash 
nix-shell -p protobuf_33
nix-shell -p protoc-gen-grpc-java

protoc --version
```


```bash 
mkdir -p DetectionApp/src/generated &&
protoc \
  --plugin=protoc-gen-grpc-java=$(which protoc-gen-grpc-java) \
  --java_out=DetectionApp/src/generated \
  --grpc-java_out=DetectionApp/src/generated \
  -I proto \
  proto/*.proto
```



```bash 
mkdir -p backend-services/src/generated

python -m grpc_tools.protoc \
  -I proto \
  --python_out=pythonservice/src/generated \
  --grpc_python_out=pythonservice/src/generated \
  proto/*.proto
```



