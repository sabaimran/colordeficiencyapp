clear all;
close all;

%% constants used for LMS - RGB conversions and read image

RGBtoLMS = [[17.8824,43.5161,4.1193]
            [3.4557,27.1554,3.8671]
            [0.02996,0.18431,1.4670]];
        
LMStoRGB = inv(RGBtoLMS);
% LMS constants from Intellignet Modification for daltonization process of
% digitized paintings
        
rgb_img = im2double(imread('apples.jpg'));
% disp(rgb_img);

%% need to do LT on each RGB pixel to get Q stimulus

Q = zeros(size(rgb_img));
% long wavelength
Q(:,:,1) = rgb_img(:,:,1)*RGBtoLMS(1,1)+rgb_img(:,:,2)*RGBtoLMS(1,2)+rgb_img(:,:,3)*RGBtoLMS(1,3);
% medium
Q(:,:,2) = rgb_img(:,:,1)*RGBtoLMS(2,1)+rgb_img(:,:,2)*RGBtoLMS(2,2)+rgb_img(:,:,3)*RGBtoLMS(2,3);
% short 
Q(:,:,3) = rgb_img(:,:,1)*RGBtoLMS(3,1)+rgb_img(:,:,2)*RGBtoLMS(3,2)+rgb_img(:,:,3)*RGBtoLMS(3,3);

%% for protanopes
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
    
%% find the modified stimulus Q'

%find LMS values for D65 daylight standard illuminance
XYZ = [0.95047; 1.0000; 1.08883];

D65 = [[0.4002,0.7076,-0.808]
       [-0.2263,1.1654,0.0457]
       [0,0,0.9182]];

% equal energy white illuminant (possibly)
D65LMS = D65 * XYZ;


   

