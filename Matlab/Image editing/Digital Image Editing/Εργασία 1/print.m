function print(Mer,x_cl,x_fl)
fprintf('�� ����� ����� ��� ������� Merilin �����: %d kai %d\n',min(Mer(:)),max(Mer(:)));
fprintf('�� ����� ����� ��� ������� flowers �����: %d kai %d\n',min(x_fl(:)),max(x_fl(:)));
fprintf('�� ����� ����� ��� ������� clock �����: %d kai %d\n',min(x_cl(:)),max(x_cl(:)));
mer = Balance(Mer);
clo = Balance(x_cl);
flo = Balance(x_fl);
%Figures
figure;subplot(3,2,1);imshow(uint8(Mer));subplot(3,2,2);imshow(uint8(mer));hold on;
subplot(3,2,3);imshow(uint8(x_cl));subplot(3,2,4);imshow(uint8(clo));hold on;
subplot(3,2,5);imshow(uint8(x_fl));subplot(3,2,6);imshow(uint8(flo));hold on;
end

