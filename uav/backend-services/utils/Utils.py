import cv2
import os

def imageSeqToVideo(directory, outFileName='video.avi', frameRate=25, width=1920, height=1080):
    images = sorted([img for img in os.listdir(directory) if img.endswith(".jpg")])
    frame = cv2.imread(os.path.join(directory, images[0]))
    height, width, layers = frame.shape

    video = cv2.VideoWriter(outFileName, cv2.VideoWriter_fourcc(*'DIVX'), frameRate, (width, height))

    for image in images:
        video.write(cv2.imread(os.path.join(directory, image)))

    video.release()


if __name__ == '__main__':
    dirName = 'AVG-TownCentre'
    parent = f'/home/cachy/Загрузки/MOT15/test/{dirName}'
    directory = parent + '/img1/'
    out = parent + f'/{dirName}.30fps.mp4'
    imageSeqToVideo(directory, out, 30, 1920, 1080)
