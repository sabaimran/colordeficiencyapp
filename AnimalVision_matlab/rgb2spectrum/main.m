close all;
clear all;

%rgb values
% white =     [1 1 1]';
% cyan =      [0 1 1]';
% magenta =   [1 0 1]';
% yellow =    [1 1 0]';
% red =       [1 0 0]';
% green =     [0 1 0]';
% blue =      [0 0 1]';
% % sanity check :works!  
% whiteSpectrum = rgb2spectrum(1,1,1);
% cyanS =         rgb2spectrum(0,1,1);
% magentaS =      rgb2spectrum(1,0,1);
% yellowS =       rgb2spectrum(1,1,0);
% redS =          rgb2spectrum(1,0,0);
% greenS =        rgb2spectrum(0,1,0);
% blueS =         rgb2spectrum(0,0,1);


%% the returened spectrum has 10 bins, going from wavelengths 380-720nm
% linspace(380, 720, 11)
% 380   414   448   482   516   550   584   618   652   686   720
% every bin has 34 wavelengths in it

im = imread('apples.jpg');
imd = im2double(im);
imgSpectrum = img2spectrum(imd);


% lets say we sampled the spectrum at wavelengths linspace(380, 686, 10) + 17,
% which corresponds to the middle of each bin
samplePoints = linspace(380, 686, 10) + 17;
spectrum = rgb2spectrum(0.5, 0.5, 0.5); %some color

%define sensitivities/ cones
%HVS: blue cone: 420 nm // green cone: 530 nm // red cone: 560 nm
sensitivities = [420 530 560];

% get the value of the spectrum at the cones
spectrValues = zeros(size(sensitivities));
for k=1:length(sensitivities)
    spectrValues(k) = getInterpolated(samplePoints, spectrum, sensitivities(k));
end

%%need know to get the "center of gravity", i.e combine wavelength and
%%spectral wavelength so as to get a single wavelength





