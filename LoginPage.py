import tkinter as tk
from tkinter import messagebox
from PIL import ImageTk, Image
import mysql.connector

# Establish MySQL Connection
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="root",
    database="quiz_db"
)
cursor = db.cursor()

def login_user():
    username = username_entry.get()
    password = password_entry.get()

    if username and password:
        # Store user data into the MySQL database
        query = "INSERT INTO users (username, password) VALUES (%s, %s)"
        cursor.execute(query, (username, password))
        db.commit()
        start_quiz()
    else:
        messagebox.showerror("Error", "Please enter both username and password")

# Create the login window
root = tk.Tk()
root.title("Login")
root.geometry("600x600")  # Increased window size

# Add background image
bg_image = Image.open("C:/DBMS_MiniProject/Python_DB/BGimg.jpg")
bg_image = bg_image.resize((600, 600))
bg_img = ImageTk.PhotoImage(bg_image)
bg_label = tk.Label(root, image=bg_img)
bg_label.place(relwidth=1, relheight=1)

# Username and password fields
username_label = tk.Label(root, text="Username", font=("Arial", 16))  # Increased font size
username_label.pack(pady=20)
username_entry = tk.Entry(root, font=("Arial", 14))  # Increased font size
username_entry.pack()

password_label = tk.Label(root, text="Password", font=("Arial", 16))  # Increased font size
password_label.pack(pady=20)
password_entry = tk.Entry(root, show="*", font=("Arial", 14))  # Increased font size
password_entry.pack()

login_button = tk.Button(root, text="Login", command=login_user, font=("Arial", 14), height=2, width=10)  # Increased button size
login_button.pack(pady=40)

# Quiz questions
questions = [
    ("What is a foreign key?", "A", ["A constraint", "A function", "A table", "None"]),
    ("Which SQL command is used to delete a table?", "A", ["DROP", "DELETE", "REMOVE", "TRUNCATE"]),
    ("What is the primary purpose of a database?", "A", ["To store and retrieve data", "To run applications", "To manage user interfaces", "None"]),
    ("Which SQL statement is used to extract data from a database?", "A", ["SELECT", "EXTRACT", "GET", "PULL"]),
    ("In a relational database, what does a primary key do?", "A", ["Uniquely identifies each record in a table", "Links tables together", "Stores data", "None"]),
    ("What type of relationship is represented by a foreign key in a relational database?", "D", ["One-to-one", "One-to-many", "Many-to-many", "All of the above"]),
    ("Which of the following is a NoSQL database?", "A", ["MongoDB", "MySQL", "Oracle", "SQLite"]),
    ("What is normalization in databases?", "A", ["The process of organizing data to reduce redundancy", "The process of data encryption", "The process of data backup", "None"]),
    ("Which command is used to remove a table from a database?", "A", ["DROP TABLE", "REMOVE TABLE", "DELETE TABLE", "TRUNCATE TABLE"]),
    ("Which of the following is not a type of SQL join?", "A", ["LINK JOIN", "INNER JOIN", "LEFT JOIN", "RIGHT JOIN"]),
]

user_answers = []
question_index = 0
score = 0

def next_question():
    global question_index, score
    selected_option = selected_var.get()
    if selected_option == questions[question_index][1]:
        score += 1
    
    user_answers.append(selected_option)

    question_index += 1
    if question_index >= len(questions):
        submit_results()
    else:
        show_question(question_index)

def show_question(index):
    question_label.config(text=questions[index][0])
    for i, option in enumerate(options):
        option.config(text=questions[index][2][i])
        option.deselect()  # Ensure no option is selected before showing the next question

def submit_results():
    cursor.execute("UPDATE users SET score = %s WHERE username = %s", (score, username_entry.get()))
    db.commit()
    messagebox.showinfo("Quiz Completed", f"Your score is: {score}/10")
    retry_button.pack()

def start_quiz():
    login_button.pack_forget()
    question_label.pack(pady=20)
    for option in options:
        option.pack(pady=10)  # Increased space between options
    next_button.pack(pady=40)  # Increased space before the Next button
    show_question(0)

# Create a new window for the quiz
quiz_window = tk.Toplevel(root)
quiz_window.title("DBMS Quiz")
quiz_window.geometry("600x600")  # Increased window size for the quiz

question_label = tk.Label(quiz_window, text="", wraplength=500, font=("Arial", 16))  # Increased font size
selected_var = tk.StringVar()

options = [
    tk.Radiobutton(quiz_window, text="", variable=selected_var, value="A", font=("Arial", 14)),  # Increased font size
    tk.Radiobutton(quiz_window, text="", variable=selected_var, value="B", font=("Arial", 14)),  # Increased font size
    tk.Radiobutton(quiz_window, text="", variable=selected_var, value="C", font=("Arial", 14)),  # Increased font size
    tk.Radiobutton(quiz_window, text="", variable=selected_var, value="D", font=("Arial", 14)),  # Increased font size
]

next_button = tk.Button(quiz_window, text="Next", command=next_question, font=("Arial", 14), height=2, width=10)  # Increased button size
retry_button = tk.Button(quiz_window, text="Retry", command=start_quiz, font=("Arial", 14), height=2, width=10)  # Increased button size

root.mainloop()
