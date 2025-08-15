//SCENARIO 1 - Apply 1% discount for customers above 60

DECLARE
    CURSOR customer_cursor IS
        SELECT c.customer_id, c.customer_name, c.age, l.loan_id, l.interest_rate
        FROM customers c
        INNER JOIN loans l ON c.customer_id = l.customer_id
        WHERE c.age > 60;
    
    v_old_rate NUMBER(5,2);
    v_new_rate NUMBER(5,2);
    v_discount NUMBER(5,2) := 1.0;
    v_count NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== APPLYING 1% DISCOUNT FOR CUSTOMERS ABOVE 60 ===');
    
    FOR rec IN customer_cursor LOOP
        v_old_rate := rec.interest_rate;
        v_new_rate := rec.interest_rate - v_discount;
        
        UPDATE loans 
        SET interest_rate = v_new_rate,
            last_updated = SYSDATE
        WHERE loan_id = rec.loan_id;
        
        v_count := v_count + 1;
        
        DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.customer_name || ' (Age: ' || rec.age || ')');
        DBMS_OUTPUT.PUT_LINE('  Loan ID: ' || rec.loan_id);
        DBMS_OUTPUT.PUT_LINE('  Old Rate: ' || v_old_rate || '% -> New Rate: ' || v_new_rate || '%');
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Total customers processed: ' || v_count);
    COMMIT;
END;
/

//Verify the discount was applied

SELECT c.customer_name, c.age, l.loan_id, l.interest_rate
FROM customers c
INNER JOIN loans l ON c.customer_id = l.customer_id
WHERE c.age > 60
ORDER BY c.age DESC; 

//SCENARIO 2 - Set VIP status for customers with balance over $10,000

DECLARE
    CURSOR customer_cursor IS
        SELECT customer_id, customer_name, balance
        FROM customers;
    
    v_vip_threshold NUMBER := 10000;
    v_count NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== UPDATING VIP STATUS FOR HIGH-BALANCE CUSTOMERS ===');
    DBMS_OUTPUT.PUT_LINE('VIP Threshold: $' || v_vip_threshold);
    
    FOR rec IN customer_cursor LOOP
        IF rec.balance > v_vip_threshold THEN
            UPDATE customers 
            SET is_vip = 'TRUE',
                vip_since = SYSDATE,
                last_updated = SYSDATE
            WHERE customer_id = rec.customer_id;
            
            v_count := v_count + 1;
            
            DBMS_OUTPUT.PUT_LINE('VIP GRANTED: ' || rec.customer_name || ' (Balance: $' || rec.balance || ')');
        ELSE
            DBMS_OUTPUT.PUT_LINE('No VIP: ' || rec.customer_name || ' (Balance: $' || rec.balance || ')');
        END IF;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Total VIP customers created: ' || v_count);
    COMMIT;
END;
/

//Verify VIP status updates

SELECT customer_name, balance, is_vip, 
       TO_CHAR(vip_since, 'DD-MON-YYYY') as vip_since_formatted
FROM customers
ORDER BY balance DESC;


//SCENARIO 3 - Send reminders for loans due within 30 days

DECLARE
    CURSOR loan_cursor IS
        SELECT l.loan_id, l.customer_id, l.loan_amount, l.due_date,
               c.customer_name, c.email, c.phone,
               TRUNC(l.due_date - SYSDATE) as days_until_due
        FROM loans l
        INNER JOIN customers c ON l.customer_id = c.customer_id
        WHERE l.due_date <= SYSDATE + 30
        AND l.loan_status = 'ACTIVE'
        ORDER BY l.due_date;
    
    v_urgency_level VARCHAR2(20);
    v_reminder_count NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== LOAN PAYMENT REMINDERS (Next 30 Days) ===');
    
    FOR rec IN loan_cursor LOOP
        IF rec.days_until_due <= 5 THEN
            v_urgency_level := 'CRITICAL';
        ELSIF rec.days_until_due <= 15 THEN
            v_urgency_level := 'HIGH';
        ELSE
            v_urgency_level := 'MEDIUM';
        END IF;
        
        INSERT INTO loan_reminders (loan_id, customer_id, urgency_level, days_before_due)
        VALUES (rec.loan_id, rec.customer_id, v_urgency_level, rec.days_until_due);
        
        v_reminder_count := v_reminder_count + 1;
        
        DBMS_OUTPUT.PUT_LINE('REMINDER #' || v_reminder_count || ' - ' || v_urgency_level);
        DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.customer_name);
        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || rec.loan_id || ', Amount: $' || rec.loan_amount);
        DBMS_OUTPUT.PUT_LINE('Due Date: ' || TO_CHAR(rec.due_date, 'DD-MON-YYYY'));
        DBMS_OUTPUT.PUT_LINE('Days Until Due: ' || rec.days_until_due);
        DBMS_OUTPUT.PUT_LINE('Contact: ' || rec.email || ', ' || rec.phone);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------');
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Total reminders sent: ' || v_reminder_count);
    COMMIT;
END;
/

//View all generated reminders

SELECT r.reminder_id, r.loan_id, c.customer_name, r.urgency_level, 
       r.days_before_due, TO_CHAR(r.reminder_date, 'DD-MON-YYYY HH24:MI:SS') as reminder_sent
FROM loan_reminders r
INNER JOIN customers c ON r.customer_id = c.customer_id
ORDER BY r.urgency_level, r.days_before_due;


//Final Summary Report

SELECT 'VIP Customers' as category, COUNT(*) as count
FROM customers WHERE is_vip = 'TRUE'
UNION ALL
SELECT 'Customers with Discounted Rates' as category, COUNT(DISTINCT c.customer_id) as count
FROM customers c
INNER JOIN loans l ON c.customer_id = l.customer_id
WHERE c.age > 60
UNION ALL
SELECT 'Loan Reminders Generated' as category, COUNT(*) as count
FROM loan_reminders;


//Final Data Verification

SELECT c.customer_name, c.age, c.balance, c.is_vip,
       l.loan_id, l.loan_amount, l.interest_rate,
       CASE WHEN r.reminder_id IS NOT NULL THEN 'YES' ELSE 'NO' END as reminder_sent
FROM customers c
LEFT JOIN loans l ON c.customer_id = l.customer_id
LEFT JOIN loan_reminders r ON l.loan_id = r.loan_id
ORDER BY c.age DESC, l.loan_id;


