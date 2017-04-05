close all;
clear all;

%rgb values
white =     [1 1 1]';
cyan =      [0 1 1]';
magenta =   [1 0 1]';
yellow =    [1 1 0]';
red =       [1 0 0]';
green =     [0 1 0]';
blue =      [0 0 1]';

% % sanity check :works!  
% whiteSpectrum = toSpectrum(1,1,1);
% cyanS =         toSpectrum(0,1,1);
% magentaS =      toSpectrum(1,0,1);
% yellowS =       toSpectrum(1,1,0);
% redS =          toSpectrum(1,0,0);
% greenS =        toSpectrum(0,1,0);
% blueS =         toSpectrum(0,0,1);

im = imread('apples.jpg');
imd = im2double(im);

imgSpectrum = imgToSpectrum(imd);





