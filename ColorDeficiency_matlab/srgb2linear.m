function linear = srgb2linear(srgb)
% srgb: the input image in sRGB space, must be in double in the
% range [0,1]

linear = zeros(size(srgb));
mask = srgb <= 0.04045;
linear(mask) = srgb(mask)/12.92;
linear(~mask) = ((srgb(~mask)+ 0.055)/1.055).^2.4;
%normalize to range [0,1]
linear=(linear-min(linear(:)))/(max(linear(:))-min(linear(:)));
end