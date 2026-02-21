# Final System Architecture

## Overview
The system follows a defense-in-depth security model to detect and prevent SQL Injection attacks.

## Layers

1. User Layer – Sends request to system.
2. Firewall Layer – AWS WAF blocks malicious SQL patterns.
3. Distribution Layer – CloudFront acts as entry point.
4. Application Layer – EC2 runs secure backend with input validation.
5. Database Layer – RDS stores data securely.
6. Monitoring Layer – CloudWatch logs suspicious activity.
7. Alert Layer – SNS sends email notifications.

## Security Model
Defense-in-Depth: Multiple independent security layers protect the application.