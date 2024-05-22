<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poker.PokerModel"%>
<%
PokerModel model = (PokerModel) request.getAttribute("model");
String label = model.getButtonLabel();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Poker</title>
	<style>
		@import url('https://fonts.googleapis.com/css2?family=DotGothic16&display=swap');
		h1{
			font-family: "DotGothic16", sans-serif;
			font-weight: 400;
			font-style: normal;
			font-size: 100px;
		}
		p{
			color: #FFFFFF;
		}
		body{
			background: #000000;
		}
		
		.hoge {
			animation: blinkAnimeS2 2s infinite alternate;
		}
		@keyframes blinkAnimeS2{
			0%{ color : red;   background:white }
			10%{ color : white; background:red   }
			20%{ color : red;   background:white }
			30%{ color : white; background:red   }
			40%{ color : red;   background:white }
			50%{ color : white; background:red   }
			60%{ color : red;   background:white }
			70%{ color : white; background:red   }
			80%{ color : red;   background:white }
			90%{ color : white; background:red   }
			100%{ color : red;   background:white }
		}
	</style>
</head>
<body>
	<p>ポーカーゲーム</p>
	<hr>
	<h1 class="hoge">GAME OVER</h1>
	<hr>
	<p><strong>成績</strong></p>
	<p>ゲーム回数：<%=model.getGames()%></p>
	<p>チップ：<%=PokerModel.getChips()%></p>
	<hr>
	<a href="PokerServlet">NEW GAME</a>
	<br>
	<a href="/s2232106/index.html">終了</a>
	<audio autoplay src="Sounds/gameover.mp3" type="audio/mp3"></audio>
</body>
</html>