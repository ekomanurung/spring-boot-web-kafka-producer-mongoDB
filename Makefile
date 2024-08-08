run-app:
	docker-compose -f docker-compose.yml up -d

stop-app:
	docker-compose -f docker-compose.yml down -v

.phony: run-app stop-app