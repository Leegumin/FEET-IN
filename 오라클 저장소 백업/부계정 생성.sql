-----------------�ΰ��� ����
CREATE USER gumin
--��й�ȣ�� �ݵ�� �빮�� ����
IDENTIFIED BY Mingu1234567890
DEFAULT TABLESPACE DATA
TEMPORARY TABLESPACE TEMP;

--������ ���� Ǯ����
ALTER USER gumin QUOTA UNLIMITED ON "DATA";
--���� ����
GRANT CONNECT, RESOURCE TO gumin;