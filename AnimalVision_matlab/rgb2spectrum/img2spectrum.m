function img = img2spectrum(in)

[a b c] = size(in);
img = zeros(a,b, 10); %for every pixel we will have its spectrum representation


for x=1:a
    for y= 1:b
        red = in(x,y,1);
        green = in(x,y,2);
        blue = in(x,y,3);
        img(x,y,:) = rgb2spectrum(red, green, blue);
    end
end



end