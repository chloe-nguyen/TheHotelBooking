USE [master]
GO
/****** Object:  Database [THE_HOTEL_BOOKING]    Script Date: 11/8/2020 10:05:19 PM ******/
CREATE DATABASE [THE_HOTEL_BOOKING]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'THE_HOTEL_BOOKING', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\THE_HOTEL_BOOKING.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'THE_HOTEL_BOOKING_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\THE_HOTEL_BOOKING_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [THE_HOTEL_BOOKING].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET ARITHABORT OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET  ENABLE_BROKER 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET  MULTI_USER 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET DB_CHAINING OFF 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET QUERY_STORE = OFF
GO
USE [THE_HOTEL_BOOKING]
GO
/****** Object:  Table [dbo].[account]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[email] [varchar](255) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[fullName] [varchar](255) NOT NULL,
	[phoneNumber] [varchar](12) NOT NULL,
	[address] [varchar](255) NULL,
	[createDate] [date] NOT NULL,
	[role] [bit] NOT NULL,
	[statusId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cartId]  AS ('CART'+right('00000000'+CONVERT([varchar](8),[id]),(8))) PERSISTED,
	[email] [varchar](255) NOT NULL,
	[checkinDate] [date] NOT NULL,
	[checkoutDate] [date] NOT NULL,
	[cartDate] [date] NOT NULL,
	[discountId] [varchar](20) NULL,
	[statusId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cartDetail]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cartDetail](
	[cartDetailId] [int] IDENTITY(1,1) NOT NULL,
	[cartId] [int] NOT NULL,
	[roomDetailId] [int] NULL,
	[quantity] [int] NOT NULL,
	[price] [decimal](18, 0) NOT NULL,
	[statusId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cartDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[discount]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[discount](
	[discountId] [varchar](20) NOT NULL,
	[discountName] [varchar](max) NOT NULL,
	[discountPercent] [int] NOT NULL,
	[createDate] [date] NOT NULL,
	[exp] [date] NULL,
	[statusId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[discountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hotel]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hotel](
	[hotelId] [int] NOT NULL,
	[hotelName] [varchar](50) NOT NULL,
	[hotelAddress] [varchar](255) NOT NULL,
	[hotelEmail] [varchar](255) NOT NULL,
	[hotelPhoneNumber] [varchar](12) NOT NULL,
	[hotelImage] [varchar](255) NULL,
	[regionId] [int] NOT NULL,
	[statusId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[hotelId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[region]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[region](
	[regionId] [int] NOT NULL,
	[regionName] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[regionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roomDetail]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roomDetail](
	[roomDetailId] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [decimal](18, 0) NOT NULL,
	[image] [varchar](255) NULL,
	[roomTypeId] [int] NOT NULL,
	[hotelId] [int] NOT NULL,
	[statusId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roomDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roomType]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roomType](
	[roomTypeId] [int] NOT NULL,
	[roomTypeName] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roomTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[status]    Script Date: 11/8/2020 10:05:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[status](
	[statusId] [int] NOT NULL,
	[statusName] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD FOREIGN KEY([statusId])
REFERENCES [dbo].[status] ([statusId])
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD FOREIGN KEY([discountId])
REFERENCES [dbo].[discount] ([discountId])
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[account] ([email])
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD FOREIGN KEY([statusId])
REFERENCES [dbo].[status] ([statusId])
GO
ALTER TABLE [dbo].[cartDetail]  WITH CHECK ADD FOREIGN KEY([cartId])
REFERENCES [dbo].[cart] ([id])
GO
ALTER TABLE [dbo].[cartDetail]  WITH CHECK ADD FOREIGN KEY([roomDetailId])
REFERENCES [dbo].[roomDetail] ([roomDetailId])
GO
ALTER TABLE [dbo].[cartDetail]  WITH CHECK ADD FOREIGN KEY([statusId])
REFERENCES [dbo].[status] ([statusId])
GO
ALTER TABLE [dbo].[discount]  WITH CHECK ADD FOREIGN KEY([statusId])
REFERENCES [dbo].[status] ([statusId])
GO
ALTER TABLE [dbo].[hotel]  WITH CHECK ADD FOREIGN KEY([regionId])
REFERENCES [dbo].[region] ([regionId])
GO
ALTER TABLE [dbo].[hotel]  WITH CHECK ADD FOREIGN KEY([statusId])
REFERENCES [dbo].[status] ([statusId])
GO
ALTER TABLE [dbo].[roomDetail]  WITH CHECK ADD FOREIGN KEY([hotelId])
REFERENCES [dbo].[hotel] ([hotelId])
GO
ALTER TABLE [dbo].[roomDetail]  WITH CHECK ADD FOREIGN KEY([roomTypeId])
REFERENCES [dbo].[roomType] ([roomTypeId])
GO
ALTER TABLE [dbo].[roomDetail]  WITH CHECK ADD FOREIGN KEY([statusId])
REFERENCES [dbo].[status] ([statusId])
GO
USE [master]
GO
ALTER DATABASE [THE_HOTEL_BOOKING] SET  READ_WRITE 
GO
