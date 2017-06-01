% generate the spectrum plots used in the report

whiteSpectrum =     [1.0000 1.0000 0.9999 0.9993 0.9992 0.9998 1.0000 1.0000 1.0000 1.0000 1.0]; 
cyanSpectrum =      [0.9710 0.9426 1.0007 1.0007 1.0007 1.0007 0.1564 0.0000 0.0000 0.0000 0.0];
magentaSpectrum =   [1.0000 1.0000 0.9685 0.2229 0.0000 0.0458 0.8369 1.0000 1.0000 0.9959 0.9959];
yellowSpectrum =    [0.0001 0.0000 0.1088 0.6651 1.0000 1.0000 0.9996 0.9586 0.9685 0.9840 0.9840];
redSpectrum =       [0.1012 0.0515 0.0000 0.0000 0.0000 0.0000 0.8325 1.0149 1.0149 1.0149 1.0149];
greenSpectrum =     [0.0000 0.0000 0.0273 0.7937 1.0000 0.9418 0.1719 0.0000 0.0000 0.0025 0.0025];
blueSpectrum =      [1.0000 1.0000 0.8916 0.3323 0.0000 0.0000 0.0003 0.0369 0.0483 0.0496 0.0496];
%needed to repeat the last value of the spectra because of the stairs
%function

X = linspace(380,720, 11);

figure
hold on
stairs(X, whiteSpectrum, 'black', 'Linewidth', 2, 'Linestyle', '-')
stairs(X, cyanSpectrum, 'cyan', 'Linewidth', 2,  'Linestyle', ':')
stairs(X, magentaSpectrum, 'magenta','Linewidth', 2, 'Linestyle', '-.')
stairs(X, yellowSpectrum, 'yellow', 'Linewidth', 2, 'Linestyle', '--')
legend('White', 'Cyan', 'Magenta', 'Yellow')
hold off

figure
hold on
stairs(X, redSpectrum, 'red', 'Linewidth', 2)
stairs(X, greenSpectrum, 'green', 'Linewidth', 2, 'Linestyle', '-.')
stairs(X, blueSpectrum, 'blue', 'Linewidth', 2, 'Linestyle', '--')
legend('Red', 'Green', 'Blue')
hold off