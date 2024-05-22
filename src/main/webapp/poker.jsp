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
	body{
		background-color: #008000;
		color: #FFFFFF;
	}
	a{
		color: white;
	}
</style>
</head>
<body>
	ポーカーゲーム
	<hr>
	ゲーム回数：<%=model.getGames()%>
	<br> チップ：<%=PokerModel.getChips()%>
	<hr>
	<%=model.getMessage()%>
	<form action="/s2232106/PokerServlet" method="POST">
		<table>
			<tr>
				<!-- カード全体がチェックボックスの判定 -->
				<td><label for="box0"><img
						src="cards/<%=model.getHandcardAt(0)%>.png" width="100"
						height="150"></label></td>
				<td><label for="box1"><img
						src="cards/<%=model.getHandcardAt(1)%>.png" width="100"
						height="150"></label></td>
				<td><label for="box2"><img
						src="cards/<%=model.getHandcardAt(2)%>.png" width="100"
						height="150"></label></td>
				<td><label for="box3"><img
						src="cards/<%=model.getHandcardAt(3)%>.png" width="100"
						height="150"></label></td>
				<td><label for="box4"><img
						src="cards/<%=model.getHandcardAt(4)%>.png" width="100"
						height="150"></label></td>
			</tr>
			<%
			if (model.getButtonLabel().equals("交換")) {
			%>
			<tr align="center">
				<td><input type="checkbox" name="change" value="0" id="box0"></td>
				<td><input type="checkbox" name="change" value="1" id="box1"></td>
				<td><input type="checkbox" name="change" value="2" id="box2"></td>
				<td><input type="checkbox" name="change" value="3" id="box3"></td>
				<td><input type="checkbox" name="change" value="4" id="box4"></td>
			</tr>
			<%
			}
			%>
		</table>
		<%
		if (model.getButtonLabel().equals("次のゲーム")) { //チェックボックス非表示の分改行
		%>
		<br>
		<%
		}
		%>
		<input type="submit" value="<%=label%>" id="submitButton">
	</form>
	<hr>
	<a href="/s2232106/PokerServlet">リセット</a>
	<br>
	<a href="/s2232106/index.html">終了</a>
	<audio autoplay src="Sounds/card.mp3" type="audio/mp3"></audio>
	<audio autoplay src="Sounds/BGM.wav" type="audio/wav"></audio>
	<!-- Enterで交換、次のゲームへ進めるようjsを追加 -->
	<script>
		// Enterキーが押されたときの処理
		document.addEventListener("keypress", function(event) {
			// キーコードがEnterキーに対応する13であるかを確認
			if (event.keyCode === 13) {
				// 特定のボタンのクリックイベントを発火させる
				document.getElementById("submitButton").click();
			}
		});
	</script>
</body>
</html>
