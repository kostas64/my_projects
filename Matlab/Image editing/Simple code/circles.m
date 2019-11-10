function  [image] = circles( image , N , M )
d = zeros(400,400); %������� ���������� ��� ��� �� pixel
a = 200/(M+N);      %������ ������ 

     for i=1:a  
       for x = 1 : 400
         for y = 1 : 400
             z = (x-200).^2;
             c = (y-200).^2;
             
                 d (x,y) = sqrt( z + c ); %����������� ���������� ����������
             
                 if  d(x,y) > (i-1)*(N+M)+N  &&  d(x,y)<= (i-1)*(N+M)+N+M %������� ��������� pixel ��� ������ � ��
                     image(x,y) = 0;    %���� �����
                     drawnow;
                end
         end
       end
     end
     
    imshow(image); %�������� �������
end

