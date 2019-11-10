function [a] = convert( path )

%������� ������� ���� buffer 
v=VideoReader(path);
%�������� ������� ��� ��� buffer
video = read(v);
%����������� video ���� ��� �����������
implay(video);
[rows,columns,~,frames] = size(video);
 a = zeros(rows,columns,frames);
 
 for i = 1:frames
     %��������� ���� frame �� grayscale
     a(:,:,i) = rgb2gray(video(:,:,:,i));
 end
 %����������� ���� ��� ��������� �� grayscale
 implay(uint8(a));

end

