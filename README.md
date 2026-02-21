"Cloud-Based SQL Injection Detection & Prevention Using AWS"
-->Project Overview
This project demonstrates a real-world, cloud-based security architecture designed to detect and prevent SQL Injection attacks using AWS services and secure coding practices.
It implements a defense-in-depth model by combining:
*Application-level protection (PreparedStatement)

*Infrastructure-level isolation (VPC + Security Groups)

*Managed cloud database (Amazon RDS)

*Reverse proxy layer (CloudFront)

*Web Application Firewall (AWS WAF)



The system includes both:
🔓 A vulnerable implementation (for demonstration)
🔒 A secure implementation (for mitigation)
This allows practical comparison of attack vs protection.

-->Architecture
User
→ CloudFront (CDN layer – optional)
→ AWS WAF (Firewall layer – optional)
→ EC2 (Java Backend)
→ RDS MySQL (Private Database inside VPC)
<img width="1024" height="1536" alt="Architecture_Diagram" src="https://github.com/user-attachments/assets/72b1e43c-fd6d-48bc-ba84-2c2cfe6b775e" />

-->Core Security Layers
Edge Layer – CloudFront + WAF (traffic inspection)
Application Layer – Secure coding with PreparedStatemen
Network Layer – VPC + Security Groups
Data Layer – Private RDS MySQL
This architecture follows the Defense in Depth principle.

-->Technologies Used
Java (JDK 17)
JDBC (MySQL Connector)
AWS EC2
AWS RDS (MySQL)
AWS VPC
AWS Security Groups
AWS CloudFront (optional)
AWS WAF (optional)

Demonstration: Vulnerable vs Secure
 Vulnerable Version (Port 8082)

Uses dynamic SQL concatenation:
String sql = "SELECT * FROM users WHERE username='" 
             + username + "' AND password='" 
             + password + "'";
Injection Test
/login?username=' OR '1'='1&password=anything

Result:
Login successful

The attacker bypasses authentication.
Secure Version (Port 8081)

Uses parameterized queries:
String sql = "SELECT * FROM users WHERE username=? AND password=?";
PreparedStatement stmt = conn.prepareStatement(sql);
Injection Test
/login?username=' OR '1'='1&password=anything

Result:
Login failed

SQL injection is prevented because input is treated as data, not executable SQL.
Security Controls Implemented
1️⃣ Application-Level Protection
PreparedStatement
Parameter binding
Input decoding

2️⃣ Database Isolation
RDS deployed inside VPC
Public access disabled
Security group restricted to EC2 only

3️⃣ Network-Level Security
Port-based access control
Least privilege security groups
Controlled inbound rules

4️⃣ Optional Edge Protection
AWS WAF SQL Injection Rule Set
CloudFront as reverse proxy
Traffic filtering before backend

-->How It Solves Real-World Problems:
SQL Injection remains one of the most common web vulnerabilities.
This architecture demonstrates how modern cloud systems protect against it by:
Blocking malicious requests at the edge (WAF)
Preventing unsafe query execution (PreparedStatement)
Isolating databases inside private networks
Restricting access using security groups
This model can be applied to:
Banking portals
E-commerce platforms
Healthcare systems
Educational portals
Enterprise SaaS applications

-->Project Structure
SecureServer.java       → Injection-protected backend (Port 8081)
VulnerableServer.java   → Demonstration of SQL injection (Port 8082)
logs/                   → Security logs
mysql-connector-j.jar   → JDBC driver
Key Learning Outcomes

Through this project, the following concepts were implemented and debugged in real cloud infrastructure:
SQL injection mechanics
Secure coding practices
AWS VPC networking
Security group configuration
EC2 to RDS connectivity
JDBC driver configuration
Cloud deployment troubleshooting
Defense-in-depth architecture

Conclusion:
This project demonstrates a practical implementation of cloud-native security architecture for preventing SQL injection attacks.
It combines secure coding techniques with AWS infrastructure-level protections to build a resilient system suitable for production environments.
The comparison between vulnerable and secure implementations provides a clear understanding of how SQL injection attacks work and how they can be mitigated effectively.
