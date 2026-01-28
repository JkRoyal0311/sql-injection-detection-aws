\# System Architecture



\## Overview

The system uses a multi-layered security approach to detect and prevent

SQL Injection attacks in a cloud environment.



\## Architecture Flow

User → Authentication → AWS WAF → Application → Database → Monitoring



\## Security Layers

1\. Authentication Layer – Restricts unauthorized access

2\. Firewall Layer – AWS WAF blocks SQL Injection patterns

3\. Application Layer – Secure coding using prepared statements

4\. Database Layer – Controlled access via RDS

5\. Monitoring Layer – Logs and alerts using CloudWatch and SNS



\## Key Benefit

Even if one layer fails, other layers continue to protect the system.



