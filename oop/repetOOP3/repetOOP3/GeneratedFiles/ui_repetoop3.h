/********************************************************************************
** Form generated from reading UI file 'repetoop3.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_REPETOOP3_H
#define UI_REPETOOP3_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_repetOOP3Class
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *repetOOP3Class)
    {
        if (repetOOP3Class->objectName().isEmpty())
            repetOOP3Class->setObjectName(QStringLiteral("repetOOP3Class"));
        repetOOP3Class->resize(600, 400);
        menuBar = new QMenuBar(repetOOP3Class);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        repetOOP3Class->setMenuBar(menuBar);
        mainToolBar = new QToolBar(repetOOP3Class);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        repetOOP3Class->addToolBar(mainToolBar);
        centralWidget = new QWidget(repetOOP3Class);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        repetOOP3Class->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(repetOOP3Class);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        repetOOP3Class->setStatusBar(statusBar);

        retranslateUi(repetOOP3Class);

        QMetaObject::connectSlotsByName(repetOOP3Class);
    } // setupUi

    void retranslateUi(QMainWindow *repetOOP3Class)
    {
        repetOOP3Class->setWindowTitle(QApplication::translate("repetOOP3Class", "repetOOP3", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class repetOOP3Class: public Ui_repetOOP3Class {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_REPETOOP3_H
