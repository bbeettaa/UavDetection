


```bash
python -m grpc_tools.protoc \
                      -I ./proto \
                      --python_out=./backend-services/generated/ \
                      --grpc_python_out=./backend-services/generated/ \
                      --pyi_out=./backend-services/generated/ \
                      proto/*.proto
```

```bash 
mkdir -p backend-services/src/generated
#UavDetection/uav
python -m grpc_tools.protoc \
                      -I . \
                      --python_out=./backend-services/generated/ \
                      --grpc_python_out=./backend-services/generated/ \
                      --pyi_out=./backend-services/generated/ \
                      proto/*.proto
```

