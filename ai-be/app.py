from flask import Flask

app = Flask(__name__)


@app.route('/test')
def hello_world():  # put application's code here
    return 'THERE IS PYTHON SERVER!!'


if __name__ == '__main__':
    app.run(port=5000, debug=True)
