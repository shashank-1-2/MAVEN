<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register User</title>
    <!-- Google Fonts for Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            /* Deep Blue Gradient Background */
            background: linear-gradient(135deg, #0f0c29, #1e3c72, #2a5298);
            padding: 20px;
        }

        /* Main Card Container */
        .register-card {
            background: rgba(255, 255, 255, 0.97);
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 20px 50px rgba(0, 0, 0, 0.3);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        .register-card h2 {
            font-size: 26px;
            color: #333;
            margin-bottom: 30px;
            font-weight: 700;
        }

        /* Input Groups */
        .input-group {
            position: relative;
            margin-bottom: 20px;
            text-align: left;
        }

        .input-group label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
            color: #666;
            font-weight: 500;
            margin-left: 10px;
        }

        .input-wrapper {
            position: relative;
        }

        .input-wrapper i {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #aaa;
        }

        .input-wrapper input {
            width: 100%;
            padding: 14px 40px; /* Extra padding on right for checkmark */
            border: 2px solid #e0e0e0;
            border-radius: 30px;
            font-size: 15px;
            outline: none;
            background: #f9f9f9;
            transition: border-color 0.3s, box-shadow 0.3s;
        }

        .input-wrapper input:focus {
            border-color: #00d2ff;
            box-shadow: 0 0 8px rgba(0, 210, 255, 0.2);
        }

        /* Validation Icons */
        .validation-icon {
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            font-size: 16px;
        }

        .fa-circle-check.valid { color: #28a745; }
        .fa-circle-xmark.invalid { color: #dc3545; }

        .error-msg {
            display: none;
            color: #dc3545;
            font-size: 12px;
            margin-top: 5px;
            margin-left: 10px;
        }

        /* Submit Button */
        .btn-submit {
            width: 100%;
            padding: 15px;
            border: none;
            border-radius: 30px;
            /* Orange Gradient */
            background: linear-gradient(90deg, #ff8c00, #ff5733);
            color: white;
            font-size: 17px;
            font-weight: 600;
            cursor: pointer;
            margin-top: 15px;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(255, 87, 51, 0.4);
        }
    </style>
</head>
<body>

    <div class="register-card">
        <h2>Register User</h2>

        <form action="register" method="post">
            
            <!-- Name Field -->
            <div class="input-group">
                <label for="name">Name:</label>
                <div class="input-wrapper">
                    <i class="fa-solid fa-user"></i>
                    <input type="text" id="name" name="name" placeholder="John Doe" required>
                    <i class="fa-solid fa-circle-check validation-icon valid" id="nameValid" style="display: none;"></i>
                </div>
            </div>

            <!-- Email Field -->
            <div class="input-group">
                <label for="email">Email:</label>
                <div class="input-wrapper">
                    <i class="fa-solid fa-envelope"></i>
                    <input type="email" id="email" name="email" placeholder="example@email.com" required>
                    <i class="fa-solid fa-circle-check validation-icon valid" id="emailValid" style="display: none;"></i>
                    <i class="fa-solid fa-circle-xmark validation-icon invalid" id="emailInvalid" style="display: none;"></i>
                </div>
                <div class="error-msg" id="emailError">Invalid format</div>
            </div>

            <!-- Age Field -->
            <div class="input-group">
                <label for="age">Age:</label>
                <div class="input-wrapper">
                    <i class="fa-solid fa-calendar"></i>
                    <input type="number" id="age" name="age" placeholder="Enter your age" required>
                    <i class="fa-solid fa-circle-check validation-icon valid" id="ageValid" style="display: none;"></i>
                </div>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn-submit">Create Account</button>
        </form>
    </div>

    <!-- JavaScript for Live Validation (Exactly like the screenshot) -->
    <script>
        const emailInput = document.getElementById('email');
        const emailValid = document.getElementById('emailValid');
        const emailInvalid = document.getElementById('emailInvalid');
        const emailError = document.getElementById('emailError');

        const nameInput = document.getElementById('name');
        const nameValid = document.getElementById('nameValid');

        const ageInput = document.getElementById('age');
        const ageValid = document.getElementById('ageValid');

        // Email validation
        emailInput.addEventListener('input', function() {
            const value = this.value;
            const isValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);

            if (isValid) {
                emailInput.classList.add('valid');
                emailValid.style.display = 'block';
                emailInvalid.style.display = 'none';
                emailError.style.display = 'none';
            } else {
                emailInput.classList.add('invalid');
                emailValid.style.display = 'none';
                emailInvalid.style.display = 'block';
                emailError.style.display = 'block';
            }
        });

        // Name validation (simple non-empty check)
        nameInput.addEventListener('input', function() {
            if (this.value.length > 0) {
                nameValid.style.display = 'block';
            } else {
                nameValid.style.display = 'none';
            }
        });

        // Age validation (simple positive number check)
        ageInput.addEventListener('input', function() {
            if (this.value > 0) {
                ageValid.style.display = 'block';
            } else {
                ageValid.style.display = 'none';
            }
        });
    </script>

</body>
</html>