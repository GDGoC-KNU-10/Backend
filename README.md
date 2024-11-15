## 🛠 기술 스택

- Java 21, Spring Boot 3.x
- MySQL 8.0
- Docker
- Github Actions
- AWS (EC2, RDS)

## 🔍 주요 기능

- 회원 건강 예민도 관리
- 증상 기록 관리
- 생활습관 기록
- 비타민 복용 현황
- 병원 진료 기록
- 예방접종 현황
- 건강검진 관리
- FCM 푸시 알림

## 🔄 CI/CD 파이프라인

1. Github Actions Workflow 트리거 (dev 브랜치 push)
2. Java 빌드 및 테스트
3. Docker 이미지 빌드
4. Docker Hub 푸시
5. EC2 인스턴스 배포