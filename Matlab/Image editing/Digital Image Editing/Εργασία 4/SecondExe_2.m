function [ MOerror1,MOerror2,result1,result2 ] = SecondExe_2( image, factor )
%Mirrors
copyim1 = image;
copyim2 = image;
%Transformations
coeff1fft = fft2(copyim1);
coeff2dct = dct2(copyim2);

coeff1 = abs(coeff1fft);
coeff2 = abs(coeff2dct);
%Sorting, in order to keep max values
sortedcoeff1 = sort(coeff1(:),'descend'); 
sortedcoeff2 = sort(coeff2(:),'descend');

%Number of max coefficients
%Cast to int64 to keep all digits
choosemax1 = int64(((((size(coeff1,1) * size(coeff1,2)) * factor) / 100)));
choosemax2 = int64(((((size(coeff2,1) * size(coeff2,2)) * factor) / 100)));

zeros1 = zeros(size(image,1));
zeros2 = zeros(size(image,1));

chosencoeff1 = sortedcoeff1(1:choosemax1);
chosencoeff2 = sortedcoeff2(1:choosemax2);


%Searching coefficients position in starting image
%and builfing the new image for both transformations
    for i = 1 : size(chosencoeff1,1)
        position1 = find(chosencoeff1(i) == coeff1);
        zeros1(position1) = coeff1fft(position1);
        
        position2 = find(chosencoeff2(i) == coeff2);
        zeros2(position2) = coeff2dct(position2);
    end



result1 = ifft2(zeros1);
result2 = idct2(zeros2);

MOerror1 = sum(abs(image(:) - result1(:))) / (size(image,1) * size(image,2));
MOerror2 = sum(abs(image(:) - result2(:))) / (size(image,1) * size(image,2));

