function [ c ] = filter_avg( image )

a = fspecial ('average'); %������ 3�3 ����� ����
c = conv2(image,a,'same'); %�������� ������� ��� �������
imshow(c);

end

