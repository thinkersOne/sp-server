# sp-server

# 打包镜像
docker build -t sp-server-image .
# 执行镜像
docker run -p 8099:8099 sp-server-image

