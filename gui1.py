from PyQt5.QtWidgets import QApplication, QLabel, QWidget

app = QApplication([])
window = QWidget()
window.setWindowTitle("PyQt5 Demo")
label = QLabel("Hello, PyQt5!", parent=window)
window.show()
app.exec_()
