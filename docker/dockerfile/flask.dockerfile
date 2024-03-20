FROM python:3.12

ENV PYTHONIOENCODING=utf-8

WORKDIR /app

copy ./ai-be .

RUN pip install --upgrade pip
RUN pip install -r requirements.txt

CMD ["python", "app.py"]