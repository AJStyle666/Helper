import tkinter as tk
from tkinter import messagebox

# Function to submit form data and display info
def submit_form():
    name = name_entry.get()
    age = age_entry.get()
    gender = gender_var.get()
    languages = [lang for lang, var in lang_kn.items() if var.get() == 1]
    country = country_var.get()
    info = f"Name: {name}\nAge: {age}\nGender: {gender}\nLanguages: {', '.join(languages)}\nCountry: {country}"
    messagebox.showinfo("Form Submitted", info)

# Main application window
root = tk.Tk()
root.title("User Information Form")
root.geometry("350x450")
root.configure(bg="#f0f4f8")

# Section for Name
tk.Label(root, text="Name:", font=("Arial", 12, "bold"), bg="#f0f4f8").pack(anchor="w", padx=10, pady=5)
name_entry = tk.Entry(root, font=("Arial", 10))
name_entry.pack(fill='x', padx=10, pady=5)

# Section for Age
tk.Label(root, text="Age:", font=("Arial", 12, "bold"), bg="#f0f4f8").pack(anchor="w", padx=10, pady=5)
age_entry = tk.Entry(root, font=("Arial", 10))
age_entry.pack(fill='x', padx=10, pady=5)

# Section for Gender
tk.Label(root, text="Gender:", font=("Arial", 12, "bold"), bg="#f0f4f8").pack(anchor="w", padx=10, pady=5)
gender_var = tk.StringVar(value="Male")
gender_frame = tk.Frame(root, bg="#f0f4f8")
gender_frame.pack(anchor="w", padx=10, pady=5)
tk.Radiobutton(gender_frame, text="Male", variable=gender_var, value="Male", bg="#f0f4f8").pack(side="left")
tk.Radiobutton(gender_frame, text="Female", variable=gender_var, value="Female", bg="#f0f4f8").pack(side="left")

# Section for Known Languages
tk.Label(root, text="Known Languages:", font=("Arial", 12, "bold"), bg="#f0f4f8").pack(anchor="w", padx=10, pady=5)
lang_kn = {"Java": tk.IntVar(), "Python": tk.IntVar(), "C++": tk.IntVar()}
lang_frame = tk.Frame(root, bg="#f0f4f8")
lang_frame.pack(anchor="w", padx=10, pady=5)
for lang, var in lang_kn.items():
    tk.Checkbutton(lang_frame, text=lang, variable=var, bg="#f0f4f8").pack(side="left")

# Section for Country Dropdown
tk.Label(root, text="Country:", font=("Arial", 12, "bold"), bg="#f0f4f8").pack(anchor="w", padx=10, pady=5)
country_var = tk.StringVar(value="Select Country")
country_options = ["India", "USA", "Japan", "Brazil"]
country_dropdown = tk.OptionMenu(root, country_var, *country_options)
country_dropdown.config(font=("Arial", 10), width=15)
country_dropdown.pack(anchor="w", padx=10, pady=5)

# Submit Button
submit_btn = tk.Button(root, text="Submit", command=submit_form, font=("Arial", 10, "bold"), bg="#008cba", fg="white")
submit_btn.pack(pady=20)

root.mainloop()