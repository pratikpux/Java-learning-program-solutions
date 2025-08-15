SET SERVEROUTPUT ON;

-- SCENARIO 1: MONTHLY INTEREST PROCESSING
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
BEGIN
    UPDATE accounts 
    SET balance = balance * 1.01
    WHERE account_type = 'SAVINGS';
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Monthly interest of 1% applied to all savings accounts.');
    DBMS_OUTPUT.PUT_LINE('Number of accounts updated: ' || SQL%ROWCOUNT);
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
        RAISE;
END ProcessMonthlyInterest;
/

-- SCENARIO 2: EMPLOYEE BONUS UPDATE
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department_name IN VARCHAR2,
    p_bonus_percentage IN NUMBER
)
AS
    v_employee_count NUMBER;
BEGIN
    IF p_bonus_percentage < 0 OR p_bonus_percentage > 100 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Bonus percentage must be between 0 and 100');
    END IF;
    
    SELECT COUNT(*) INTO v_employee_count
    FROM employees 
    WHERE department = p_department_name;
    
    IF v_employee_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'No employees found in department: ' || p_department_name);
    END IF;
    
    UPDATE employees 
    SET salary = salary * (1 + p_bonus_percentage/100)
    WHERE department = p_department_name;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_percentage || '% applied to ' || SQL%ROWCOUNT || ' employees in ' || p_department_name || ' department.');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
        RAISE;
END UpdateEmployeeBonus;
/

-- SCENARIO 3: FUND TRANSFER BETWEEN ACCOUNTS
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
)
AS
    v_from_balance NUMBER;
    v_from_exists NUMBER;
    v_to_exists NUMBER;
BEGIN
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Transfer amount must be greater than 0');
    END IF;
    
    SELECT COUNT(*) INTO v_from_exists
    FROM accounts 
    WHERE account_id = p_from_account_id;
    
    SELECT COUNT(*) INTO v_to_exists
    FROM accounts 
    WHERE account_id = p_to_account_id;
    
    IF v_from_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Source account does not exist: ' || p_from_account_id);
    END IF;
    
    IF v_to_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Destination account does not exist: ' || p_to_account_id);
    END IF;
    
    SELECT balance INTO v_from_balance
    FROM accounts 
    WHERE account_id = p_from_account_id;
    
    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20006, 'Insufficient balance. Available: ' || v_from_balance || ', Required: ' || p_amount);
    END IF;
    
    UPDATE accounts 
    SET balance = balance - p_amount
    WHERE account_id = p_from_account_id;
    
    UPDATE accounts 
    SET balance = balance + p_amount
    WHERE account_id = p_to_account_id;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Successfully transferred ' || p_amount || ' from account ' || p_from_account_id || ' to account ' || p_to_account_id);
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
        RAISE;
END TransferFunds;
/