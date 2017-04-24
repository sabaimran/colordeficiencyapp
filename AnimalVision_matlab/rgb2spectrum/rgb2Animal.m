function [r, g, b] = rgb2Animal(R,G,B,sensitivities)
% rgb2Animal: transforms an RGB input triplet to a simulated rgb triplet in
% the animal vision using the specified cone sensitivities
%   R,G,B :         input triplet with values between 0 and 1
%   sensitivities:  an array with the wavelengths of the cone sensitivities
%                   of the animal.
%                   the smallest wavelength MUST be the first element and the
%                   largest the last element of the array


% lets say we sampled the spectrum at wavelengths linspace(380, 686, 10) + 17,
% which corresponds to the middle of each bin
samplePoints = linspace(380, 686, 10) + 17;
spectrum = rgb2spectrum(R, G, B); %spectrum of the specified color

%get values of spectrum at the cone sensitivities
spectrValues = zeros(size(sensitivities));
for k=1:length(sensitivities)
    spectrValues(k) = getInterpolated(samplePoints, spectrum, sensitivities(k));
end

%%need to get the "center of gravity", i.e combine wavelength and
%%spectral values so as to get a single wavelength
spectrValues = spectrValues/sum(spectrValues);
finalWavelength = sum(spectrValues .* sensitivities);
[r, g, b] = wavelength2rgb(finalWavelength);

%normalize, i.e. convert to double
r = r/255;
g = g/255;
b = b/255;

end

