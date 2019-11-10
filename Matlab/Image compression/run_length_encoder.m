function [ encoded,l ] = run_length_encoder( image )

[rows,columns] = size(image);
%�������� ��� ������ �����������, >3 ��� �� ������� ������ �������������
counter=1;
%������� �������������
encoded = [];
%������ ������ bytes
b1=rows*columns; 

 for i = 1 : rows
     for j = 2 : columns 
         if image(i,j) == image(i,j-1)
             counter=counter+1;
           %�� �������� � ��������� ��� ����� ����� ��� counter<3 (��� ������)
          %���� ���� ������ 1 � 2 ����� ��� ����
         elseif image(i,j) ~= image(i,j-1) && counter < 3
                 
                 for k = 1 : counter
                      encoded = [encoded,image(i,j-1)];
                 end
                 counter=1;
          %�� �������� � ��������� ��� ����� ����� ��� counter>3 (������)
          %���� ���� ������ �� ������, ��� ���� ��� �� ������� -1 ���
          %������������
         elseif image(i,j) ~= image(i,j-1) && counter >= 3
                 encoded = [encoded,counter];
                 encoded = [encoded,image(i,j-1)];
                 encoded = [encoded,-1];
                 counter=1;
         end
         %��������� ���������� ��� �� ��������� ����� ���� ������ 
         if j == columns && counter == 1 
              encoded = [encoded,image(i,j)];
         elseif j == columns && counter == 2
              encoded = [encoded,image(i,j)];
              encoded = [encoded,image(i,j)];
         elseif j == columns && counter > 2
              encoded = [encoded,counter];
              encoded = [encoded,image(i,j-1)];
              encoded = [encoded,-1];
         end
     end
     counter=1;
 end
 %����������� bytes ��� ���� ���������
 [~,columns] = size(encoded);
 b2 = columns;
 l = b1/b2;
end

