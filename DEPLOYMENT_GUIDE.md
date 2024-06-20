# SuperMarket System - Deployment Guide

## 🚀 Production Deployment

### Prerequisites
- Java 17 or higher installed
- Linux/Unix server (recommended)
- 512MB RAM minimum
- 1GB disk space
- Port 8080 available (or configurable)

---

## 📦 Building for Production

### 1. Build the JAR
```bash
cd /home/ashwin/SuperMarket
mvn clean package -DskipTests
```

Output: `target/supermarket-system-1.0.0.jar`

### 2. Verify Build
```bash
ls -lh target/supermarket-system-1.0.0.jar
```

---

## 🔧 Production Configuration

### Create Production Config
Create `application-prod.properties`:

```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Database
spring.datasource.url=jdbc:sqlite:/var/lib/supermarket/supermarket.db
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Logging
logging.level.root=WARN
logging.level.com.supermarket=INFO
logging.file.name=/var/log/supermarket/app.log
logging.file.max-size=10MB
logging.file.max-history=30

# JWT
jwt.secret=CHANGE_THIS_TO_STRONG_SECRET_KEY_IN_PRODUCTION
jwt.expiration=86400000

# Performance
server.tomcat.threads.max=200
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```

---

## 🐧 Linux Systemd Setup

### 1. Create Service File
```bash
sudo nano /etc/systemd/system/supermarket.service
```

Add:
```ini
[Unit]
Description=SuperMarket Management System
After=network.target

[Service]
Type=simple
User=supermarket
WorkingDirectory=/opt/supermarket
ExecStart=/usr/bin/java -jar supermarket-system-1.0.0.jar --spring.profiles.active=prod
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```

### 2. Create System User
```bash
sudo useradd -r -s /bin/false supermarket
```

### 3. Setup Directories
```bash
sudo mkdir -p /opt/supermarket
sudo mkdir -p /var/lib/supermarket
sudo mkdir -p /var/log/supermarket

sudo chown -R supermarket:supermarket /opt/supermarket
sudo chown -R supermarket:supermarket /var/lib/supermarket
sudo chown -R supermarket:supermarket /var/log/supermarket
```

### 4. Copy JAR
```bash
sudo cp target/supermarket-system-1.0.0.jar /opt/supermarket/
sudo chown supermarket:supermarket /opt/supermarket/supermarket-system-1.0.0.jar
```

### 5. Enable Service
```bash
sudo systemctl daemon-reload
sudo systemctl enable supermarket
sudo systemctl start supermarket
```

### 6. Check Status
```bash
sudo systemctl status supermarket
sudo journalctl -u supermarket -f  # Follow logs
```

---

## 🐳 Docker Setup

### Dockerfile
```dockerfile
FROM openjdk:17-slim

WORKDIR /app

COPY target/supermarket-system-1.0.0.jar app.jar
COPY src/main/resources/application-prod.properties /app/application-prod.properties

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
```

### Build Docker Image
```bash
docker build -t supermarket-system:1.0.0 .
```

### Run Container
```bash
docker run -d \
  --name supermarket \
  -p 8080:8080 \
  -v /var/lib/supermarket:/var/lib/supermarket \
  -v /var/log/supermarket:/var/log/supermarket \
  supermarket-system:1.0.0
```

---

## 🌐 Nginx Reverse Proxy

### Nginx Configuration
```nginx
upstream supermarket {
    server localhost:8080;
}

server {
    listen 80;
    server_name supermarket.example.com;

    client_max_body_size 10M;

    location / {
        proxy_pass http://supermarket;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_connect_timeout 60s;
        proxy_send_timeout 60s;
        proxy_read_timeout 60s;
    }
}
```

### Enable HTTPS (Let's Encrypt)
```bash
sudo apt-get install certbot python3-certbot-nginx
sudo certbot certonly --nginx -d supermarket.example.com
```

Update nginx:
```nginx
server {
    listen 443 ssl;
    server_name supermarket.example.com;

    ssl_certificate /etc/letsencrypt/live/supermarket.example.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/supermarket.example.com/privkey.pem;

    # ... rest of config
}
```

---

## 📊 Database Backup

### Automated Backup Script
```bash
#!/bin/bash

BACKUP_DIR="/var/backups/supermarket"
DB_PATH="/var/lib/supermarket/supermarket.db"
DATE=$(date +%Y%m%d_%H%M%S)

mkdir -p $BACKUP_DIR

cp $DB_PATH $BACKUP_DIR/supermarket_$DATE.db

# Keep only last 30 days
find $BACKUP_DIR -name "supermarket_*.db" -mtime +30 -delete
```

### Cron Job
```bash
0 2 * * * /opt/supermarket/backup.sh
```

---

## 🔐 Security Checklist

- [ ] Change JWT secret in application-prod.properties
- [ ] Use HTTPS (SSL/TLS)
- [ ] Set strong admin password
- [ ] Enable firewall
- [ ] Regular database backups
- [ ] Monitor logs
- [ ] Update dependencies regularly
- [ ] Restrict API access if needed
- [ ] Enable rate limiting
- [ ] Set up monitoring/alerts

---

## 📈 Performance Optimization

### Database
```properties
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.fetch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```

### Connection Pool
```properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

### Caching
```properties
spring.cache.type=simple
spring.cache.cache-names=products,sections
```

---

## 🚨 Monitoring

### Application Monitoring
```bash
# Check memory usage
ps aux | grep java

# Check disk usage
df -h

# Check logs
tail -f /var/log/supermarket/app.log
```

### Uptime Monitoring
```bash
# Simple health check
curl http://localhost:8080/api/health
```

### Performance Metrics
Add Spring Boot Actuator:
```properties
management.endpoints.web.exposure.include=health,metrics,info
management.endpoint.health.show-details=always
```

---

## 🔄 Upgrade Process

### 1. Backup Current
```bash
sudo systemctl stop supermarket
sudo cp /var/lib/supermarket/supermarket.db /var/lib/supermarket/supermarket.db.backup
```

### 2. Deploy New Version
```bash
sudo cp target/supermarket-system-1.1.0.jar /opt/supermarket/
sudo chown supermarket:supermarket /opt/supermarket/supermarket-system-1.1.0.jar
```

### 3. Update Service
Update ExecStart in `/etc/systemd/system/supermarket.service` with new version

### 4. Restart
```bash
sudo systemctl daemon-reload
sudo systemctl start supermarket
```

### 5. Verify
```bash
sudo systemctl status supermarket
```

---

## 📋 Deployment Checklist

- [ ] JAR built successfully
- [ ] Production config created
- [ ] Database directory created
- [ ] Log directory created
- [ ] Service file created
- [ ] System user created
- [ ] JAR file permissions set
- [ ] Service enabled
- [ ] Service started
- [ ] Health check passing
- [ ] HTTPS configured
- [ ] Backups scheduled
- [ ] Monitoring configured
- [ ] Logs monitored
- [ ] Firewall configured

---

## 🆘 Troubleshooting Deployment

### Service Won't Start
```bash
sudo journalctl -u supermarket -n 50
sudo systemctl status supermarket
```

### Database Connection Error
```bash
# Check database file exists
ls -la /var/lib/supermarket/supermarket.db

# Check permissions
ls -la /var/lib/supermarket/
```

### Port Already in Use
```bash
sudo lsof -i :8080
sudo kill -9 <PID>
```

### Out of Memory
Update Java args:
```bash
ExecStart=/usr/bin/java -Xmx512m -Xms256m -jar ...
```

---

## 📞 Production Support

For production issues:

1. Check logs: `/var/log/supermarket/app.log`
2. Check system resources
3. Verify database integrity
4. Check network connectivity
5. Review recent changes

---

## 🎉 Ready for Production!

Your SuperMarket System is now ready for:
- ✅ Production deployment
- ✅ High availability
- ✅ Scaling
- ✅ Monitoring
- ✅ Backup & recovery

**Happy Deploying! 🚀**
