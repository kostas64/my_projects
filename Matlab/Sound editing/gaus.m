function [ z,y ] = gaus( x )

z=zeros(900000,2); %���������� ������ ���
snr = 30;          %�������� ������� �� �����

z= awgn(z,snr); %���� �������
plot(z);

y = awgn(x,snr);    %������ ���� �� ������ Gaus

figure; subplot(6,1,1); plot(x(:,1)); subplot(6,1,2);plot(y(:,1));subplot(6,1,3); plot(x(:,2)); ...
subplot(6,1,4); plot(y(:,2)); subplot(6,1,5); plot(x); subplot(6,1,6); plot(y);
end

