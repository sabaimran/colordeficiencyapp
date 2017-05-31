%%% Author: Chiara Orvati, 231027

close all;
clear all;

%% read image and transform to double 
im = imread('fruits.jpg');
imd = im2double(im);

%% define sensitivities/ cones
%HVS: 
%(blue: 435 nm // green : 546 nm // red: 700 nm)
%DEER: 455 nm // 537 nm
sensitivities = [380 445 508 565]; %ASCENDING ORDER!

%% convert the input image to the simulated rgb values
animalImg = img2Animal(imd, sensitivities);

%%  convert original and simulated image to CIELAB space: 
% keep luminance of the original, take a, b from simulated and transform back.
original_lab = rgb2lab(imd);
simulated_lab= rgb2lab(animalImg);

finalAnimalVision = zeros(size(im));
finalAnimalVision(:,:,1) = original_lab(:,:,1);
finalAnimalVision(:,:,2) = simulated_lab(:,:,2);
finalAnimalVision(:,:,3) = simulated_lab(:,:,3);

finalAnimalVision = lab2rgb(finalAnimalVision);

imshow(finalAnimalVision);





