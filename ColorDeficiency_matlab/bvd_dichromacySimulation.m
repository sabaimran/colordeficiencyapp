clear all;
close all;
%%% author: Saba Imran

%% constants used for LMS - RGB conversions and read image

RGBtoLMS = [[17.8824,43.5161,4.1193]
            [3.4557,27.1554,3.8671]
            [0.02996,0.18431,1.4670]];
        
LMStoRGB = inv(RGBtoLMS);
% LMS constants from Intellignet Modification for daltonization process of
% digitized paintings
        
img_name = 'apples';

rgb_img = im2double(imread(strcat(img_name, '.jpg')));
% disp(rgb_img);

%% need to do LT on each RGB pixel to get Q stimulus

Q = zeros(size(rgb_img));
% long wavelength
Q(:,:,1) = rgb_img(:,:,1)*RGBtoLMS(1,1)+rgb_img(:,:,2)*RGBtoLMS(1,2)+rgb_img(:,:,3)*RGBtoLMS(1,3);
% medium
Q(:,:,2) = rgb_img(:,:,1)*RGBtoLMS(2,1)+rgb_img(:,:,2)*RGBtoLMS(2,2)+rgb_img(:,:,3)*RGBtoLMS(2,3);
% short 
Q(:,:,3) = rgb_img(:,:,1)*RGBtoLMS(3,1)+rgb_img(:,:,2)*RGBtoLMS(3,2)+rgb_img(:,:,3)*RGBtoLMS(3,3);

%% for protanopes (missing L) from Intellignet Modification for daltonization process of digitized paintings
PROT = [[0,2.02344,-2.5281]
        [0,1,0]
        [0,0,1]];
QP = zeros(size(rgb_img));

%find new stimulus for protanope
%long
QP(:,:,1) = Q(:,:,1)*PROT(1,1)+Q(:,:,2)*PROT(1,2)+Q(:,:,3)*PROT(1,3);
% medium
QP(:,:,2) = Q(:,:,1)*PROT(2,1)+Q(:,:,2)*PROT(2,2)+Q(:,:,3)*PROT(2,3);
% short 
QP(:,:,3) = Q(:,:,1)*PROT(3,1)+Q(:,:,2)*PROT(3,2)+Q(:,:,3)*PROT(3,3);

%find corresponding RGB value
RGBP = zeros(size(rgb_img));
%long
RGBP(:,:,1) = QP(:,:,1)*LMStoRGB(1,1)+QP(:,:,2)*LMStoRGB(1,2)+QP(:,:,3)*LMStoRGB(1,3);
% medium
RGBP(:,:,2) = QP(:,:,1)*LMStoRGB(2,1)+QP(:,:,2)*LMStoRGB(2,2)+QP(:,:,3)*LMStoRGB(2,3);
% short 
RGBP(:,:,3) = QP(:,:,1)*LMStoRGB(3,1)+QP(:,:,2)*LMStoRGB(3,2)+QP(:,:,3)*LMStoRGB(3,3);


%% check protanope
figure
subplot 211
imshow(rgb_img)
subplot 212
imshow(RGBP)
imwrite(im2uint8(RGBP), strcat(img_name, '_protanope.jpg'))

%% for deuteranopes (missing M) from Review of Color Blindness Removal Methods using Image Processing
DEUT = [[1,0,0]
        [0.494207,0,1.24827]
        [0,0,1]];
QD = zeros(size(rgb_img));

%find new stimulus for deut
%long
QD(:,:,1) = Q(:,:,1)*DEUT(1,1)+Q(:,:,2)*DEUT(1,2)+Q(:,:,3)*DEUT(1,3);
% medium
QD(:,:,2) = Q(:,:,1)*DEUT(2,1)+Q(:,:,2)*DEUT(2,2)+Q(:,:,3)*DEUT(2,3);
% short 
QD(:,:,3) = Q(:,:,1)*DEUT(3,1)+Q(:,:,2)*DEUT(3,2)+Q(:,:,3)*DEUT(3,3);

%find corresponding RGB value
RGBD = zeros(size(rgb_img));
%long
RGBD(:,:,1) = QD(:,:,1)*LMStoRGB(1,1)+QD(:,:,2)*LMStoRGB(1,2)+QD(:,:,3)*LMStoRGB(1,3);
% medium
RGBD(:,:,2) = QD(:,:,1)*LMStoRGB(2,1)+QD(:,:,2)*LMStoRGB(2,2)+QD(:,:,3)*LMStoRGB(2,3);
% short 
RGBD(:,:,3) = QD(:,:,1)*LMStoRGB(3,1)+QD(:,:,2)*LMStoRGB(3,2)+QD(:,:,3)*LMStoRGB(3,3);


%% check deuteranope
figure
subplot 211
imshow(rgb_img)
subplot 212
imshow(RGBD)
imwrite(im2uint8(RGBD), strcat(img_name, '_deuteranope.jpg'))


%% for tritanopes (missing S) from Review of Color Blindness Removal Methods using Image Processing
TRIT = [[1,0,0]
        [0,1,0]
        [-0.012245,0.072035,0]];
QT = zeros(size(rgb_img));

%find new stimulus for deut
%long
QT(:,:,1) = Q(:,:,1)*TRIT(1,1)+Q(:,:,2)*TRIT(1,2)+Q(:,:,3)*TRIT(1,3);
% medium
QT(:,:,2) = Q(:,:,1)*TRIT(2,1)+Q(:,:,2)*TRIT(2,2)+Q(:,:,3)*DEUT(2,3);
% short 
QT(:,:,3) = Q(:,:,1)*TRIT(3,1)+Q(:,:,2)*TRIT(3,2)+Q(:,:,3)*TRIT(3,3);

%find corresponding RGB value
RGBT = zeros(size(rgb_img));
%long
RGBT(:,:,1) = QT(:,:,1)*LMStoRGB(1,1)+QT(:,:,2)*LMStoRGB(1,2)+QT(:,:,3)*LMStoRGB(1,3);
% medium
RGBT(:,:,2) = QT(:,:,1)*LMStoRGB(2,1)+QT(:,:,2)*LMStoRGB(2,2)+QT(:,:,3)*LMStoRGB(2,3);
% short 
RGBT(:,:,3) = QT(:,:,1)*LMStoRGB(3,1)+QT(:,:,2)*LMStoRGB(3,2)+QT(:,:,3)*LMStoRGB(3,3);


%% check deuteranope
figure
subplot 211
imshow(rgb_img)
subplot 212
imshow(RGBT)
imwrite(im2uint8(RGBT), strcat(img_name, '_tritanope.jpg'))


%% find the modified stimulus Q'

%find LMS values for D65 daylight standard illuminance
XYZ = [0.95047; 1.0000; 1.08883];

D65 = [[0.4002,0.7076,-0.808]
       [-0.2263,1.1654,0.0457]
       [0,0,0.9182]];

% equal energy white illuminant (possibly)
D65LMS = D65 * XYZ;


   

