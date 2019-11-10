function [Rf,Gf,Bf] = ThirdExe( image, R0 )
%Enter loop for first time
userchoice = 1;
newimage = image;
while ( userchoice == 1 )
   
    temp = image;
    %Cast to double, because vectors are 
    %type of uint8 with max value 255.
    temp = double(temp);
    %Chosen pixels
    [~,~,P] = impixel(newimage);
    %Open GUI to choose color for replacement
    chosenColor = uisetcolor([1 0 1],'Select a color');  
    %Average value of selected pixels
    %for each vector
    avg_value_r = mean(P(:,1));
    avg_value_g = mean(P(:,2));
    avg_value_b = mean(P(:,3));
    %Rounding values
    avg_value_r = round(avg_value_r);
    avg_value_g = round(avg_value_g);
    avg_value_b = round(avg_value_b);
        %Distances for each vector
        %from the average value
        Rf = abs((temp(:,:,1) - avg_value_r));
        Gf = abs((temp(:,:,2) - avg_value_g));
        Bf = abs((temp(:,:,3) - avg_value_b));
        %Computation of square for each vector
        Rf = power(Rf,2);
        Gf = power(Gf,2);
        Bf = power(Bf,2);
        %Sum of vectors to compare with the
        %square of radius
        Distances = Rf + Gf + Bf;
     
        for i = 1 : size(image(:,:,1))
            for j = 1 : size(image(:,:,1))
                %Choose which values to change
                if Distances(i,j) <= R0^2
                    newimage(i,j,1) = round(255 * chosenColor(1));
                    newimage(i,j,2) = round(255 * chosenColor(2));
                    newimage(i,j,3) = round(255 * chosenColor(3));
                end
            end
        end
        
    %Result
    imshow(uint8(newimage));
    %}
    %Input dialog to continue or not the process
    userchoice = inputdlg('Enter 1 to continue or 0 to terminate:','Answer:', 1, {num2str('0')});
    userchoice = str2double(userchoice{1});

end

