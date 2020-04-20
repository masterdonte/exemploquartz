CREATE TABLE [dbo].[platesocr](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[eventid] [bigint] NOT NULL UNIQUE,
	[timeshot] [datetime2](7) NULL,
	[typereader] [varchar](2) DEFAULT ('O') NULL,
	[device] [varchar](10) NULL,
	[plate] [varchar](10) NULL,
	[image] [varbinary](max) NULL,
	[sent] [bit] DEFAULT 0
)

CREATE TABLE [JOB_WACCESS].[dbo].[devicecamera](
	[id] [int] IDENTITY(1,1) NOT NULL,	
	[deviceid] [int] NOT NULL,
	[devicetos] [varchar](10) NULL UNIQUE,
	[description] [varchar](30) NULL,
)

INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES ( 2, 'HOC0210', 'OCR - PAN ENTRADA 02');
INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES ( 3, 'HOC0212', 'OCR - PAN SAIDA');
INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES ( 4, 'HOC0087', 'OCR - PRC BALANCA');
INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES ( 5, 'HOC0206', 'OCR - PRC ENTRADA [2]');
INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES ( 6, 'HOC0131', 'OCR - BALANCA PRIMARIA 03');
INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES ( 7, 'HOC0129', 'OCR - BALANCA PRIMARIA 02');
INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES ( 8, 'HOC0089', 'OCR - BALANCA PRIMARIA 01');
INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES (11, 'HOC0208', 'OCR - PRC SAIDA');
INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description]) VALUES (12, 'HOC0204', 'OCR - PRC ENTRADA [1]');

INSERT INTO [JOB_WACCESS].[dbo].[devicecamera] ([deviceid] ,[devicetos] ,[description])
SELECT [deviceid], 'HCO9999', [description] FROM [EMPLAC].[dbo].[DEVICES]