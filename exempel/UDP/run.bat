@echo off
chcp 65001 > NUL
query 192.168.0.229 5005 %1
