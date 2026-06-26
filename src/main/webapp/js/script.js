// ==========================================
// 1. パスワードの「表示/非表示」切り替え
// ==========================================
const passwordInput = document.getElementById('password');
const toggleCheckbox = document.getElementById('togglePassword');

if (toggleCheckbox && passwordInput) {
	toggleCheckbox.addEventListener('change', function() {
		if (this.checked) {
			passwordInput.type = 'text';
		} else {
			passwordInput.type = 'password';
		}
	});
}

// ==========================================
// 2. パスワードの入力リアルタイムバリデーション
// ==========================================
if (passwordInput) {
	passwordInput.addEventListener('input', function() {
		const val = this.value;
		const errorSpan = document.getElementById('passwordError');
		const regex = /^[a-zA-Z0-9]*$/;

		if (!regex.test(val)) {
			errorSpan.textContent = '⚠️ パスワードは半角英数字で入力してください。';
		} else if (val.length > 0 && (val.length < 1 || val.length > 32)) {
			// ★1〜32文字の仕様に変更
			errorSpan.textContent = '⚠️ パスワードは1文字以上32文字以内で入力してください。';
		} else {
			errorSpan.textContent = '';
		}
	});
}

// ==========================================
// 3. 会員IDの入力リアルタイムバリデーション
// ==========================================
const memberIdInput = document.getElementById('memberId');
if (memberIdInput) {
	memberIdInput.addEventListener('input', function() {
		const val = this.value;
		const errorSpan = document.getElementById('memberIdError');
		const regex = /^[a-zA-Z0-9]*$/;

		if (!regex.test(val)) {
			errorSpan.textContent = '⚠️ 会員IDは半角英数字で入力してください。';
		} else if (val.length > 0 && val.length > 10) {
			// ★10文字以内の仕様に変更
			errorSpan.textContent = '⚠️ 会員IDは10文字以内で入力してください。';
		} else {
			errorSpan.textContent = '';
		}
	});
}

// ==========================================
// 4. カート数量自動更新
// ==========================================
function changeQty(btn, diff) {

	const form = btn.closest("form");
	const input = form.querySelector("input[name='quantity']");

	let value = parseInt(input.value);

	value += diff;

	if (value < 1) {
		value = 1;
	}

	input.value = value;

	// 自動送信
	form.submit();
}

function submitForm(input) {
	input.closest("form").submit();
}

// ==========================================
// 5. 商品詳細画面用の数量変更（自動送信しない版）
// ==========================================
function changeQtyDetail(btn, diff) {
	// ボタンの親要素（divクラス等）から quantity という名前の input を探す
	const container = btn.parentElement;
	const input = container.querySelector("input[name='quantity']");

	let value = parseInt(input.value);
	value += diff;

	// 1未満にはならないようにガード
	if (value < 1) {
		value = 1;
	}

	input.value = value;
	// カート画面と違って、ここでは form.submit() は呼び出しません（値を変えるだけ！）
}


// ==========================================
// 6. 画像反映用
// ==========================================
// 関数として外に出す
function updateImageDisplay(fileName) {
	const imagePathInput = document.getElementById('imagePath');
	const fileNameDisplay = document.getElementById('fileNameDisplay');

	// 画面にタグが存在するときだけ値をセットする（エラー防止）
	if (imagePathInput) {
		imagePathInput.value = fileName;
	}

	if (fileNameDisplay) {
		const h1Element = document.querySelector('h1');
		let prefix = "選択済み";
		if (h1Element && (h1Element.innerText.includes("編集") || h1Element.innerText.includes("変更"))) {
			prefix = "変更後";
		}
		fileNameDisplay.innerText = prefix + ": " + fileName;
	}
}

// ① 画像が新しく選択されたとき
const filePickerElement = document.getElementById('filePicker');
// ★超重要：画面に filePicker が存在する「商品登録画面」のときだけ動かす！
if (filePickerElement) {
	filePickerElement.addEventListener('change', function(e) {
		const file = e.target.files[0];
		
		if (file) {
			updateImageDisplay(file.name);
			// ブラウザが戻ったときのために、セッションストレージ（一時メモリ）に保存しておく
			sessionStorage.setItem('temp_imagePath', file.name);
		}
	});
}

// ② history.back() で画面に戻ってきたときに自動実行される処理
window.addEventListener('pageshow', function() {
	// 画面に filePicker があるときだけ一時メモリの復元を行う
	if (document.getElementById('filePicker')) {
		const savedImagePath = sessionStorage.getItem('temp_imagePath');
		if (savedImagePath) {
			updateImageDisplay(savedImagePath);
		}
	}
});