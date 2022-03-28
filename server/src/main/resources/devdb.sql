-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2021-09-10 08:20:26
-- 服务器版本： 10.4.19-MariaDB
-- PHP 版本： 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `devdb`
--

-- --------------------------------------------------------

--
-- 表的结构 `base_dept`
--

CREATE TABLE `base_dept` (
  `id` int(11) NOT NULL,
  `create_date` date DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `base_user`
--

CREATE TABLE `base_user` (
  `id` int(11) NOT NULL,
  `create_date` date DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `public_key` varchar(255) NOT NULL COMMENT '公钥',
  `name` varchar(255) NOT NULL COMMENT '登录名',
  `password` varchar(848) NOT NULL COMMENT '密码',
  `role` varchar(255) DEFAULT NULL COMMENT '角色',
  `dept_id` varchar(255) DEFAULT NULL COMMENT '部门id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `base_user`
--

INSERT INTO `base_user` (`id`, `create_date`, `create_user`, `update_date`, `update_user`, `public_key`, `name`, `password`, `role`, `dept_id`) VALUES
(2, NULL, NULL, NULL, NULL, 'JNdWmeFMdLHu+bwYd/UGeQ9f9lgNHHy0781d2JCUpTe0ij8/O0bbBVeM9q3wPBeLZtXR9u5wTY7HuizJDryzxElLEiHmrL5qvqZQp8/oyDmB49phCFIqqr5oV3DanjSCBKwGH5OHBt0LI3aLkcEIg8YKqQQw6qXov+jUUoi0MnY=', 't', 'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJfc3MN1SD94TgrqpnRzph+oP7pSpuoHaopp0BNOJohrmca63YWiTVNBBWymHliQor80EpPMSNBJQ1eP42q5kc+fo3VW+2nOYFO3OT+GmG3EzhR+FpqD8WlPKpfqQo6XfoQJJFEs/04hjv7VZbt61gqXoIDiLNgYCC9Kbty+lmMFAgMBAAECgYAPi8+ZvsbfoHw0x+K0ZyZpdTrY4x8tNAcnFj/zyftN2d4LJxQIWx4ZWL2qarMOk/flJPqF4NLPZ8CaFSPNyCNx+dsrEE1+7sUiPQyiyDGDfJeZ0mVMVUrAlNeOi3TDHPzTQePXAj2/mYNyPxw3zol2sNOJk+MplCWqcR39MiEFfQJBAOe1qDZ9Rm1VKiFsoT5BZ8Qzu86/mjKtZRQnu+h6tggRAeE9S4bwHIQJHUDSFSYIKwks2FZfpoRd7/h7A/1R2bsCQQCnyF8jcpsIDqwxJOIr8He/FTLLYZTN//x2OgVLCB60I0hIEM8A9dM0A+MV4B71BJ7xxH/HCD52WcASjl+ZiYo/AkEAkMuKFJLwgb4ol76EfJzutUMG58oAGA8XDSNPX5XfTuch35koK2i8yR3Q9ALUSHHuQsuNXVxkeG8jWIm3EoQlpQJAWzuFIA1NZ5Ay6XYY1bOoKWspd5XUCD/N06uw/R+pMmKNB0K8jNUexwXmERsROkIM+4HRyTQeYtRisLRiH2a+mQJBANFoCrlzjl+zL+AAPZaioSpotjktocwfxVPr5s1j9lg11/UG+cZb9tmi0KGtUZjwdyUa2fqqT6D9QAfMm4Wt83w=', '22', NULL),
(10, NULL, NULL, NULL, NULL, 'JH1sHeA/tmlKLEg1EAv1OE0YO4iwpa+da0U+LvUlGE0SQoaOLdZHAr3ANN2nm6dsKqPmYbe5v+OEhhmEvD/dSscYNkVlwhJh/Yww6yCNTTLC60k1nH3MkEbxjgOVJRtlmq77TfcQ167prJt1GrsZRZ21yONfvma053wyDPirVqI=', 'd12', 'MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIIuGkFSQ10DJ+D7/hvOmRQ7NjllNeOeeUXeN2cgL6guxZl++S5dqmGFMppGvekmnGRxbX3FLfBHMoHAjd6BlpeVTm8JU24pVNaUiJaNyl6GYE3Kfyr098UWF3DjWdmO8efpD+8QMFcqdAPZPolybL981qrm5/IMhDJbmkchb6AbAgMBAAECgYBYxTteI8yP1qERCIVlTOKSKzZ0uRGgX+ZuwHOjPQ2CcDKK00z0UybmTUAOeAL0xEGNNNPnGdiTqtlEJZwYiOG5DSyXTb+Um7H44+gsE5A24mZ/aFHWxAfy/KNJJxgNdnP90+QXpYs61gqki3UUmHNs8i+QDhBz2XO6ksmSms6MsQJBAM+az9vnaRVssyulNcBLNN9OgVPnO50Iy9g2FhMaMMGJU80tOv+Mae6IpCwpqPOtM+qtKQVmptSVFwTZCOzlLO8CQQCghtW1mkqZ5qsjUSgGWsvr/DFEHp0bp9skQAXNdTGAN2SwuFPjWNNqMi8q+E252m0TT4TxI/q/RkH9p75tLReVAkAF99FZ5QEkWIeeDZXJ/V97u+oD23XfQc16XbOswOf96FC8aTx09DhnXw45Jslwl94SNQAk45qQKekLfqG/DaeJAkAhg0TGfwmoC6Ia42yJtxbvk1ztrb568ZYsgTk05YyKCgZ5ZmINCpRIjWWPgPspG9K9v1IvzaUhnZdVT1nQDSPpAkB10RPsnvcnGHJEsM670po012PDrd2BcS2VBPhfWB83Irnt0XphEI8CZmPGinffVaehszQn1dAwwtcoEDkHtdxX', '333', NULL),
(18, NULL, NULL, NULL, NULL, 'Wi7akp7Qa5hYpJdTeovQW57Ke0NH93KwFRmGmKmf5NRG+dncjS+TakuSbKpaAYH6RWsL8P4Kv7EHl1DG4zaB8rM7B+wCKt2NR0pHM1K0AbJbEzwSiXzcjtoLZleN3W6+iLtCq3kpcFJO6svk6bC43N91h97M4nqL9bd0vPDm//4=', '21', 'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJKbn409sjqoznXngNf/Uumlj2GnqdJrDcYxRk7mKoLhAFcSxyhw5qy9cpVk9ofoHTaIlouvKHjLaO45Ltjc4G5IGuOVIj30EOmuRGyXKwPwUUS6jQ3Lyf4GzIee9mgnAwWAh/t3YbF+ByrQ0f68MNfw3RSRmMzHBjbeWcJNP6zBAgMBAAECgYBPIn9x7r54mUuwKsDKVoA7q+kEDktFUBHETC6mQejqcmcLmRA1TRPGZBAogbHs1CaInBue1HIlGa7O/8DAwCDHtTEZMHz4SSedKKH7Y6U6MsLr0BfyBKYrmxA7WMfSsQ3/PCDsL+hcuEs6ROuLdaBzi0BlvVfN1eySAMjBREC30QJBAMIWKFUZSzuDd5JtQzlv8r+X2DavWANX+m/Ejm/REgWCUwuPt00zoNDbwN69blZb17RuK+4ZAAojuT9GoQ2OnOUCQQDBYC3yyBCoj15SWphjRuACbDfU2rEJ4swCkgafamD8pBcGbWQJYQduLI93k/bavDEuK4cGJ2TzSaTT2MtIjK6tAkAttqJRSN/baJ+lMW7//EokrlMSrkIAp5wo7xbNZ2TjP0roEjWOTXa3Oss2q09tr7wil0BpmCHWQYvER5JvawOxAkEAlsM8r5cJXdGBESXuvx/n0Bu9H6ds9a8rrSlaHnmaqf3osashnipW16/IHstuXzTwBvYKrGKUUxVse3n/a027dQJBAJxwofSWRVKlaqOXtd+V9uX3bz6PO211ELYlCL7QtkmeZfLQeep5utEWpjibv5X3YOztDlKRz9mUAVhGH1ffIeM=', '33', NULL),
(19, NULL, NULL, NULL, NULL, 'WPCEKQUtvfB/l0CWr38jnedvZs13ygHK2jiSJR53jFI3wOk3I4I9+dQeD5uedplALOdI0AomoncnolOXam7UObKST9be/6JkQLyINoVM4H6nmmb9zbp1JcQuTGY8PAFO9hgDdZe9E4QQeC92Ra1zDaBmqxMEdCFtbpa0e7vbbp8=', 'b', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJGo0rad3sFIlStiJvt0uoSvTC+EP44xJ5GX2Bw9sTSonXuI2YvliBWq35Jm+5U2P/9F92XgFdIw97KpZjO1mvJpGqMRNhGQtfq+g2VgUwBKYaPcjy8ykYX8UXUyZXhPOmTUHLj+clLyUFpBDPDCAh1h747vXYV98SABE5WzRSanAgMBAAECgYAr/bHN53YMJ/CtTpJ5ZTKjuhGnVCihuCS8KwDg3pzzS21sjtMtFiB4csfc/m9DDERdw3LJeNj+Rz233sN4IIPv97s7OYXW/bP2/UA6qJAwxD8Gzijxy5enYq5CsbekL8G0vc8PMCJuNAJx5/ECf8ka0C1MHE57bqoXZ5IR0UEKyQJBAMKt5raWsFOR2u8F77AHhQaigUeg8BifOWkG0TEXQf09g14FZ4c7qkQKdekEJrEXw/j66S4edh07nlM/fupACdUCQQC/iix+aOVP/H+2W1QTgzJCn8HBpXE9L3oB+KKVKT+aBJU1oZtulCCpm8LQdm/a4gMZQXiOXqkvdofaU+jNGJCLAkA0EHwOQtifUPejSwwiILCz6Iv4kxMDErow23Dg1x5/0o6qe3mHki5kCgQksEaEwB1aHgh0nBLsmIAsNWQE0+GNAkEAioX+mcl5FnjxUEwKqTX4EnEBqu/gzrRYU7aitqenRNdNpgTjqXxdhuyMX0FYsf9E6G4OJ0DUsOvTYpXOxw2d8wJANvkHXiCsoFPOmESvwsOqtu24JWBP5CiQtuJ07y9PVgqvWeKzXlh9T+pwlaNbxss1e3eW0NVtx51TTnGy2WRGbA==', 'bcc', NULL),
(20, NULL, NULL, '2021-06-28', 20, 'AqHR39uYGYwtRm9U5DkUQzA2xc8LoL2buE3QPpmx2nrgtt//ZE3f6O1kqJ1y7r/FcmDRLLXQ5d0HreeuXaUqR7LRkscy4im0ObLjzAVllG4Db45nRh4ewDN58C3M6LMbGvtvFSP91aRXJapUL85PeWSVPkVOVARyQaH+wf6dps4=', 'cc', 'MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL3eKAMJJC+4K1HbMG6MnNP5rIiAP98EqFMolXVjwEJ9YvIDR7HnBub3c54OaoJ7BCdmmXxz/5UUBPW5KFxkTNnSnMN0eGGuQMEGZqYf5zor+Sy9LKvFCSlVMF1GYx24Nf7SGeXdAYAKwO1Bt7boKp28YKt+YSfWfOU95TvNbMYxAgMBAAECgYA6CC/UlbOwvirHbyZDc8jvZU2dvw1GngbQvAPKiH2YFPjsHaKJNqK+b32wHpnhXbYsUMiy6EWDDEZj0H8Mlj+CgRcpAtnUf3rgXxzzCNxHEPkC+e/V0sz76kXHiAMlLo+M7+Pfzl6E0OMdWflUFtG4CnalYHanl0+RJqnHYvDD3QJBAO417rm1D0K+Cw704dZMJ7XQo4VO1O86DSOOs/Vj/PXreNbWoGqjbsq478CnwnJATvCuWigaITZ09We6vTklO9cCQQDMDAN9+3/MZ7vVM8FgRhj5684hMmimJ3aVutdDiw5IVUprkJHbPM+bGo6Ln3M5tdJDX8+UDnXGXpswwLzcVQ03AkBMWCSmiAzYCyhoJruqfsXLU47UzqsX8Lb9SmMKpn0QI+Oalzbadn6vog3U5O9AxrOGF5r1d0pltZ5hHExA4FoJAkAXL5pJ+4UXHgZ7AImNvglihMgEf6zijINuGjARzcBOudpeBKx+RBe3ITpnZTP7r8x3uXBl+4qoguGRlaUgSE3bAkAgGmtiUmU+TA8f/BjHQed9cb/KnbBBcCmL6fH2dmC19UeaeqJC9TZNUzXbWPpbq/tRX2Lf1yCnO+XyKnESFPlw', 'cc', NULL),
(21, '2021-06-28', 20, NULL, NULL, 'hfPrILkAk+RPIwuiuVVcO3XzrWp4a5xqKCmqwHOeD53zq3xmHWShPl5FYpKk4E8VgZKnX0R3hRz0bUU1Ckc7otO9ZMz33gkhznZcMLQocIxRf2AlrYQIoEz6S0iW7UUxJyBIQB6s/eZwj2jaQlDIkGKKDgnXWbN8u9d87j8tMNI=', 'aa', 'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJAulCJfgm4HdRji5NDbzENL/6WBQ0Wxtg0i2+sh5QOEGAB5QxweuNu1iop9jnz/oXHLo0APiFdogPg8cP186PDf3I1OoPf+uqd+WQXSm4620N/Qk0xYGfrwkLTe2FGLj4ui9jJ6xZtXRRZFkfamhqSN158OROPB2VSILBQmjO0JAgMBAAECgYB2ggJ6GjzPiUujD+N5FCBZdB8FfzC3GTGErJFMO8jL0ggUY5dYhpv04sujtz2l2kttiC5/mgS7q4bOAofwonmnPbajZ9O7jS57NS/IrzxlzWJ2+iMueQ9nIb+ggx3nIxX+x+Mz7YeckaDaJJjjKc4K8MQqrXcRd9SLy2Uq3y1HXQJBANEB/hmpO51iC5gq7Ldy/TSniI372MJSVCcgGDhbI3A0Fxkp0aj455tS3nN+KWH1q7+GOJkG4RJeVyhZ3wC/1N8CQQCwmVwrDa+ECygRwVpscaROLsaj7Pp2jVozU1XqiYt1jtu6i1UVDvSZYmaPDHhOGUX0CtkaXMc0T6xImdGNw9MXAkEAtsIBSLr8T+Adt09Bch/wXIxqEY04uqJ2x0d/Jjg7Qq+pyu0QUapkCyNpNKAK24eQSIa7EsQ7Jm6r07LSFrunDQJAY7MBBNFiIHLH+VYmfYie0QC5ZjxYnmto3AcVPybN092GEUeuDgh2cM3LxsCsfrLn+kHRlX+Bevfr9MTaOGWwmwJBAMd1gTUTXwOfNgfB7KD18nFzz1y1h8u/uTHR1lXxL4r61RZvVvOGJUzJNEt1tKZAWKtgnyFqlG8t/WssrN9MTwg=', 'a', NULL);

--
-- 转储表的索引
--

--
-- 表的索引 `base_dept`
--
ALTER TABLE `base_dept`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `base_user`
--
ALTER TABLE `base_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `base_dept`
--
ALTER TABLE `base_dept`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `base_user`
--
ALTER TABLE `base_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
