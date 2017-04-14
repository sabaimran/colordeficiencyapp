function spectrum =rgb2spectrum(red, green, blue)
% converts an rgb triplet to a spectrum with 10 bins, ranging from 380nm to
% 720 nm
% ressource: "RGB to spectrum for reflectances" by Brian Smits
% red, green, and blue must be bewtween 0 and 1!

if(~(0 <= red && red <= 1 && 0 <= green && green <= 1 && 0 <= blue && blue <= 1))
    error('values of red, green blue must be between 0 and 1');
end

whiteSpectrum =     [1.0000 1.0000 0.9999 0.9993 0.9992 0.9998 1.0000 1.0000 1.0000 1.0000];
cyanSpectrum =      [0.9710 0.9426 1.0007 1.0007 1.0007 1.0007 0.1564 0.0000 0.0000 0.0000];
magentaSpectrum =   [1.0000 1.0000 0.9685 0.2229 0.0000 0.0458 0.8369 1.0000 1.0000 0.9959];
yellowSpectrum =    [0.0001 0.0000 0.1088 0.6651 1.0000 1.0000 0.9996 0.9586 0.9685 0.9840];
redSpectrum =       [0.1012 0.0515 0.0000 0.0000 0.0000 0.0000 0.8325 1.0149 1.0149 1.0149];
greenSpectrum =     [0.0000 0.0000 0.0273 0.7937 1.0000 0.9418 0.1719 0.0000 0.0000 0.0025];
blueSpectrum =      [1.0000 1.0000 0.8916 0.3323 0.0000 0.0000 0.0003 0.0369 0.0483 0.0496];


spectrum = zeros(size(whiteSpectrum));

if(red <= green && red <= blue)
    spectrum = spectrum + red * whiteSpectrum;
    if(green <= blue)
        spectrum = spectrum + (green - red) * cyanSpectrum;
        spectrum = spectrum + (blue - green) * blueSpectrum;
    else
        spectrum = spectrum + (blue - red) * cyanSpectrum;
        spectrum = spectrum + (green - blue) * greenSpectrum;
    end
    
elseif(green <= red && green <= blue)
    spectrum = spectrum + green * whiteSpectrum;
    if(red <= blue)
        spectrum = spectrum + (red - green) * magentaSpectrum ;
        spectrum = spectrum + (blue - red) * blueSpectrum;
    else 
        spectrum = spectrum + (blue - green) * magentaSpectrum;
        spectrum = spectrum + (red - blue) * redSpectrum; 
    end
    
else %blue  <= red && blue  <= green
    spectrum = spectrum + blue * whiteSpectrum;
    if (red <= green)
        spectrum = spectrum + (red - blue) * yellowSpectrum;
        spectrum = spectrum + (green - red) * greenSpectrum;
    else
        spectrum = spectrum + (green -blue) * yellowSpectrum;
        spectrum = spectrum + (red - green) * redSpectrum;
    end
end
end