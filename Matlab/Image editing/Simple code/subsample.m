function [image] = subsample( image )
newImage = image(1:16:end,1:16:end,:); %�������������������� ���� 16
    imshow(newImage);                  %������ ������� ������� 400/16 � 400/16
end

