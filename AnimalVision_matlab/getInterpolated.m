%%% Author: Chiara Orvati, 231027

function interpolated = getInterpolated(samplePoints, spectrum, x)
% returns the value of the spectrum at wavelength x using interpolation
% x must be between 380-720nm
% samplePoints : the wavelengths at which the spectrum has been sampled
% spectrum:      the discrete values of the spectrum
% x:             the wavelength at which we want to know the value of the
%                spectrum (most probably not one of the samplePoints)

if(~(320 <= x && x<= 720))
    error('x must be between 320 and 720 nm)')
end


% within these borders we can interpolate
leftBorder = min(samplePoints);
rightBorder = max(samplePoints);

if (leftBorder<= x && x<=rightBorder)
    interpolated = interp1(samplePoints, spectrum, x, 'pchip');
else
    interpolated = interp1(samplePoints, spectrum, x, 'pchip', 'extrap');
end