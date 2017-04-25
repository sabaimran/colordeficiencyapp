%%% Author: Chiara Orvati, 231027

function img = img2Animal(in, sensitivities)
% transforms every RGB triplet of the input image to the simulated animal
% vision rgb triplet
% in           : input image in double
% sensitivities: an array with the wavelengths of the cone sensitivities
%                of the animal.
%                the smallest wavelength MUST be the first element and the
%                largest the last element of the array

[Nx, Ny, Nz] = size(in);
img = zeros(Nx,Ny, Nz); 

for x=1:Nx
    for y= 1:Ny
        R = in(x,y,1);
        G = in(x,y,2);
        B = in(x,y,3);
        [r, g, b] = rgb2Animal(R, G, B, sensitivities);
        img(x,y,1) = r;
        img(x,y,2) = g;
        img(x,y,3) = b;
        
    end
end

end