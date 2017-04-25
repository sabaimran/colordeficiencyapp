%%% Author: Chiara Orvati, 231027


function [r, g ,b] = wavelength2rgb(lambda)
% lambda is the wavelength in nm and ranges from 380 to 720 nm
% to every 10 nm one rgb value is associated
% the rgb values returned are between 0 and 255
%
% using the values provided by
% "https://academo.org/demos/wavelength-to-colour-relationship/"

if(lambda < 380 || lambda >720)
    error('lambda out of bounds');
end

l = floor(lambda/10);

% for every bin of 10 values the value at wavelength xx5 nm has been taken
% ex: for the bin 380-389nm, the value at 385 nm was taken
switch l
    %wavelength from 380-389 nm
    case 38
        r = 111;
        g = 0;
        b = 119;
    case 39
        r = 128;
        g = 0;
        b = 161;
    case 40
        r = 130;
        g = 0;
        b = 200;
    case 41
        r = 118;
        g = 0;
        b = 237;
    case 42 
        r = 84;
        g = 0;
        b = 255;
    case 43
        r = 35;
        g = 0;
        b = 255;
    case 44
        r = 0;
        g = 40;
        b = 255;
    case 45
        r = 0;
        g = 97;
        b = 255;
    case 46
        r = 0;
        g = 146;
        b = 255;
    case 47
        r = 0;
        g = 192;
        b = 255;
    case 48
        r = 0;
        g = 234;
        b = 255;
    case 49
        r = 0;
        g = 255;
        b = 203;
    case 50
        r = 0;
        g = 255;
        b = 84;
    case 51
        r = 31;
        g = 255;
        b = 0;
    case 52
        r = 74;
        g = 255;
        b = 0;
    case 53
        r = 112;
        g = 255;
        b = 0;
    case 54
        r = 146;
        g = 255;
        b = 0;
    case 55
        r = 179;
        g = 255;
        b = 0;
    case 56
        r = 210;
        g = 255;
        b = 0;
    case 57
        r = 240;
        g = 255;
        b = 0;
    case 58
        r = 255;
        g = 239;
        b = 0;
    case 59
        r = 255;
        g = 207;
        b = 0;
    case 60
        r = 255;
        g = 173;
        b = 0;
    case 61
        r = 255;
        g = 137;
        b = 0;
    case 62
        r = 255;
        g = 99;
        b = 0;
    case 63
        r = 255;
        g = 57;
        b = 0;
    case 64
        r = 255;
        g = 0;
        b = 0;
    case 65
        r = 255;
        g = 0;
        b = 0;
    case 66
        r = 255;
        g = 0;
        b = 0;
    case 67
        r = 255;
        g = 0;
        b = 0;
    case 68
        r = 255;
        g = 0;
        b = 0;
    case 69
        r = 255;
        g = 0;
        b = 0;
    case 70
        r = 246;
        g = 0;
        b = 0;
    case 71
        r = 228;
        g = 0;
        b = 0;
    case 72 % this represents only the value 720 nm (otherwise it would be left out, and we'd need to stop at 719nnm)
        r = 219;
        g = 0;
        b = 0;

    otherwise
        disp(lambda)
        error('no bin could be assigned');
end

end
