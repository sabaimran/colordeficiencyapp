function srgb = linear2srgb(linear)
% linear: the input image in linear rgb space, must be in double in the
% range [0,1]

srgb = zeros(size(linear));

mask = linear <= 0.0031308;
srgb(mask) = 12.92 * linear(mask);
srgb(~mask) = 1.055 * linear(~mask).^(1/2.4) - 0.055;
%normalize to range [0,1]
srgb=(srgb-min(srgb(:)))/(max(srgb(:))-min(srgb(:)));
end

