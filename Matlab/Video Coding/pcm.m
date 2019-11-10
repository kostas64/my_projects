function [ l ] = pcm( a )
    %������������ �������
 [rows,columns,frames] = size(a);
  b = zeros(rows,columns,frames);
  c = zeros(rows,columns);
  %������� 1�� frame ���� ��������� ������
  b(:,:,1) = a(:,:,1);
 
  
 for i = 2 : frames 
     %����������� �������� ������ ���������� ��������
     b(:,:,i) = a(:,:,i) - a(:,:,i-1);
 end
     

 for i = 1 : rows
     for j = 1 : columns
         %����������� bits  ���������� ��� ��� ��������/pixel
         c(i,j) = ceil(log2((max(b(i,j,2:frames)) - min(b(i,j,2:frames)) + 1)));
     end
 end
 %����� ���������
 l = (rows*columns*8*frames) / (rows*columns*8 + (frames-1)*sum(c(:)));

end

